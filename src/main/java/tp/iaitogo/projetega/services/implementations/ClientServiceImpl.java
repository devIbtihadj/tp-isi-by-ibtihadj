package tp.iaitogo.projetega.services.implementations;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tp.iaitogo.projetega.entities.Client;
import tp.iaitogo.projetega.exceptions.ClientNotFoundException;
import tp.iaitogo.projetega.repositories.ClientRepository;
import tp.iaitogo.projetega.services.ClientService;

import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client creerClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client modifierClient(Client client, Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isEmpty()){
            throw new ClientNotFoundException("Aucun client n'existe avec l'id "+id);
        }else {
            client.setId(id);
            return  clientRepository.save(client);
        }
    }

    @Override
    public void supprimerClient(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isEmpty()){
            throw new ClientNotFoundException("Aucun client n'existe avec l'id "+id);
        }else {
            clientRepository.deleteById(id);
        }
    }

    @Override
    public Client getClient(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isEmpty()){
            throw new ClientNotFoundException("Aucun client n'existe avec l'id "+id);
        }else {
            return optionalClient.get();
        }
    }

    @Override
    public Page<Client> tousLesClients(int page, int size) {
        return clientRepository.findAll(PageRequest.of(page - 1, size, Sort.by("id").descending()));
    }
}
