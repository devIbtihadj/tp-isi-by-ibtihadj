package tp.iaitogo.projetega.services;

import org.springframework.data.domain.Page;
import tp.iaitogo.projetega.entities.Client;

import java.util.HashMap;


public interface ClientService {
    Client creerClient(Client client);

    Client modifierClient(Client client, Long id);

    void supprimerClient(Long id);

    Client getClient(Long id);

    HashMap<String, Object> tousLesClients(int page, int size);
}
