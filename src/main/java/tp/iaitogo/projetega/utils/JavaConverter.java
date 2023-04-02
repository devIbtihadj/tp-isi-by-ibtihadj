package tp.iaitogo.projetega.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tp.iaitogo.projetega.entities.Client;
import tp.iaitogo.projetega.entities.Compte;
import tp.iaitogo.projetega.exceptions.ClientNotFoundException;
import tp.iaitogo.projetega.repositories.ClientRepository;
import tp.iaitogo.projetega.request.AddOrModifClientRequest;
import tp.iaitogo.projetega.request.AddOrModifCompteRequest;

import java.util.Optional;

@Component
public class JavaConverter {

    private final ClientRepository clientRepository;

    public JavaConverter(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client registerOrModifToClient(AddOrModifClientRequest request){
        Client client = new Client();
        BeanUtils.copyProperties(request, client);
        return client;
    }
    public Compte registerOrModifToCompte(AddOrModifCompteRequest request){
        Compte compte = new Compte();
        BeanUtils.copyProperties(request, compte);

        Optional<Client> optionalClient = clientRepository.findById(request.getClient_id());
        if(optionalClient.isEmpty()){
            throw new ClientNotFoundException("Aucun client n'existe avec l'id "+request.getClient_id());
        }else {
            compte.setClient(optionalClient.get());
            return compte;
        }

    }
}
