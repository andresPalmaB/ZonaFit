package zona_fit;

import zona_fit.data.ClientDAO;
import zona_fit.data.IClientDAO;
import zona_fit.domain.Client;

import java.util.Scanner;

public class MenuZonaFit {

    IClientDAO clientDAO = new ClientDAO();

    public void StartApp() {
        loopApp();
    }

    private int menuApp(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** Zona fit Admin App ***");
        System.out.println("1. Get list to Client.");
        System.out.println("2. Get Client by ID.");
        System.out.println("3. Create new Client.");
        System.out.println("4. Update Client's Information.");
        System.out.println("5. Delete Client to Database.");
        System.out.println("6. Exits App.");
        System.out.print("Select option: ");
        String option = scanner.nextLine();

        return Integer.parseInt(option);
    }

    private void loopApp(){
        boolean follow = true;
        int option;
        while (follow){
            option = menuApp();
            if (option == 6){
                follow = false;
            } else if (option > 6  || option <= 0) {
                System.out.println("Wrong option, try again!");
            } else {
                selectedOption(option);
            }
        }
        System.out.println("Close Admin System.");
    }

    private void selectedOption(int option) {
        Scanner scanner = new Scanner(System.in);
        switch (option){
            case 1 -> {
                System.out.println("*** Listar Clientes ***");
                var clients = this.clientDAO.getClients();
                clients.forEach(System.out::println);
                System.out.println();
            }
            case 2 -> {
                System.out.print("What is the Client ID: ");
                int id = Integer.parseInt(scanner.nextLine());

                var client = new Client(id);
                if (this.clientDAO.findClientById(client))
                    System.out.println("Client found: " + client + "\n");
                else
                    System.out.println("Client with ID: " + client.getIdClient() + " don't found.\n");
            }
            case 3 -> {
                System.out.print("What is the client name: ");
                String name = scanner.nextLine();
                System.out.print("What is the client lastname: ");
                String lastname = scanner.nextLine();
                System.out.print("What is the client membership: ");
                int membership = Integer.parseInt(scanner.nextLine());

                var client = new Client(name, lastname, membership);
                if (this.clientDAO.addClient(client))
                    System.out.println("Client created success.\n");
                else
                    System.out.println("Client don't created.\n");
            }
            case 4 -> {
                System.out.print("What is the Client's ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                var client = new Client(id);

                this.clientDAO.findClientById(client);
                System.out.println("This is the Client: " + client);

                System.out.print("What is the client's name to update: ");
                client.setName(scanner.nextLine());
                System.out.print("What is the client's lastname to update: ");
                client.setLastName(scanner.nextLine());
                System.out.print("What is the client's membership to update: ");
                client.setMembership(Integer.parseInt(scanner.nextLine()));

                if (this.clientDAO.updateClient(client))
                    System.out.println("Client update success.\n");
                else
                    System.out.println("Client don't update.\n");
            }
            case 5 -> {
                System.out.print("What is the Client's ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                var client = new Client(id);

                if (this.clientDAO.deleteCliente(client))
                    System.out.println("Client delete success.\n");
                else
                    System.out.println("Client don't delete.\n");
            }
        }
    }
}
