package tp.iaitogo.projetega.services;

import org.springframework.data.domain.Page;
import tp.iaitogo.projetega.entities.Client;


public interface ClientService {
    Client creerClient(Client client);

    Client modifierClient(Client client, Long id);

    void supprimerClient(Long id);

    Client getClient(Long id);

    Page<Client> tousLesClients(int page, int size);
}
