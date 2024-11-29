package zona_fit.data;

import zona_fit.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static zona_fit.connection.Connection.getConnection;

public class ClientDAO implements IClientDAO {
    @Override
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        var sql = "SELECT * FROM cliente ORDER BY idcliente";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                var client = new Client();
                client.setIdClient(rs.getInt("idcliente"));
                client.setName(rs.getString("nombre"));
                client.setLastName(rs.getString("apellido"));
                client.setMembership(rs.getInt("membresia"));
                clients.add(client);
            }
        } catch (Exception e) {
            System.out.println("Error al listar Clientes: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error to close connection.");
            }
        }
        return clients;
    }

    @Override
    public boolean findClientById(Client client) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        var sql = "SELECT * FROM cliente WHERE idcliente = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, client.getIdClient());
            rs = ps.executeQuery();
            if (rs.next()){
                client.setName(rs.getString("nombre"));
                client.setLastName(rs.getString("apellido"));
                client.setMembership(rs.getInt("membresia"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error to get Clients: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error to close connection.");
            }
        }
        return false;
    }

    @Override
    public boolean addClient(Client client) {
        PreparedStatement ps;
        var con = getConnection();
        var sql = "INSERT INTO cliente (nombre, apellido, membresia) VALUES (?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getLastName());
            ps.setInt(3, client.getMembership());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error to created client: " + e.getMessage());
        } finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error to close connection.");
            }
        }
        return false;
    }

    @Override
    public boolean updateClient(Client client) {
        PreparedStatement ps;
        var con = getConnection();
        var sql = "UPDATE cliente SET nombre = ?,  apellido = ?, membresia = ? WHERE (idcliente = ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, client.getName());
            ps.setString(2, client.getLastName());
            ps.setInt(3, client.getMembership());
            ps.setInt(4, client.getIdClient());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error to update client: " + e.getMessage());
        } finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error to close connection.");
            }
        }
        return false;
    }

    @Override
    public boolean deleteCliente(Client client) {
        PreparedStatement ps;
        var con = getConnection();
        var sql = "DELETE FROM cliente WHERE (idcliente = ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, client.getIdClient());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error to delete client: " + e.getMessage());
        } finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error to close connection.");
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IClientDAO clienteDAO = new ClientDAO();

//        Test get clients list
//        System.out.println("*** Listar Clientes ***");
//        var clientes = clienteDAO.getClient();
//        clientes.forEach(System.out::println);

//        Test find client by id
//        var client = new Client(2);
//        if (clienteDAO.findClientById(client))
//            System.out.println("Client found: " + client);
//        else
//            System.out.println("Client with ID: " + client.getIdClient() + " don't found.");

//        Test to created client
//        var client = new Client("Andres", "Palma", 300);
//        if (clienteDAO.addClient(client))
//            System.out.println("Client created success.");
//        else
//            System.out.println("Client don't created.");

//        Test to update client
//        var client = new Client(3, "Paulina", "Vega", 300);
//        if (clienteDAO.updateClient(client))
//            System.out.println("Client update success.");
//        else
//            System.out.println("Client don't update.");

//        Test to delete cliente
//        var client = new Client(3);
//        if (clienteDAO.deleteCliente(client))
//            System.out.println("Client delete success.");
//        else
//            System.out.println("Cliente don't delete.");
    }
}
