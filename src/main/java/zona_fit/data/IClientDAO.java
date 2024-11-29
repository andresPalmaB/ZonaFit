package zona_fit.data;

import zona_fit.domain.Client;

import java.util.List;

public interface IClientDAO {

    List<Client> getClients();
    boolean findClientById(Client client);
    boolean addClient(Client client);
    boolean updateClient(Client client);
    boolean deleteCliente(Client client);

}
