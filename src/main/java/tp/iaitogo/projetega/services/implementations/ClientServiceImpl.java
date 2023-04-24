package tp.iaitogo.projetega.services.implementations;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tp.iaitogo.projetega.entities.Client;
import tp.iaitogo.projetega.exceptions.ClientNotFoundException;
import tp.iaitogo.projetega.exceptions.DateFormatNotCorrectException;
import tp.iaitogo.projetega.repositories.ClientRepository;
import tp.iaitogo.projetega.services.ClientService;

import java.time.LocalDate;
import java.util.HashMap;
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
        LocalDate today = LocalDate.now();
        if(client.getDateNaissance().isAfter(today)){
            throw new DateFormatNotCorrectException("La date de naissance doit être inférieur à la date du jour");
        }
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
    public HashMap<String, Object> tousLesClients(int page, int size) {
        Page<Client> pageClients = clientRepository.findAll(PageRequest.of(page - 1, size, Sort.by("id").descending()));
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("currentPage", page);
        hashMap.put("totalPages", pageClients.getTotalPages());
        hashMap.put("totalElements", pageClients.getTotalElements());
        hashMap.put("clients", pageClients.getContent());
        return hashMap;
    }
}
