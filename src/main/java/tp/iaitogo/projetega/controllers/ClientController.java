package tp.iaitogo.projetega.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.iaitogo.projetega.entities.Client;
import tp.iaitogo.projetega.handlers.MyCustomExceptionsHandler;
import tp.iaitogo.projetega.request.AddOrModifClientRequest;
import tp.iaitogo.projetega.services.ClientService;
import tp.iaitogo.projetega.utils.JavaConverter;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ClientController extends MyCustomExceptionsHandler {


    private final String ENTITY_PATH = "client";

    private final ClientService clientService;
    private final JavaConverter javaConverter;

    @Autowired
    public ClientController(ClientService clientService, JavaConverter javaConverter) {
        this.clientService = clientService;
        this.javaConverter = javaConverter;
    }

    @PostMapping(value = ENTITY_PATH+"/create", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Client> creerClient(@RequestBody AddOrModifClientRequest clientFromApi) {
        Client response = clientService.creerClient(javaConverter.registerOrModifToClient(clientFromApi));
        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping(value = ENTITY_PATH+"/get/{id}", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {
        Client response = clientService.getClient(id);
        return ResponseEntity.status(OK).body(response);
    }

    @PutMapping(value = ENTITY_PATH+"/update/{id}", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Client> modifierClient(@RequestBody AddOrModifClientRequest clientFromApi, @PathVariable("id") Long id) {
        Client response = clientService.modifierClient(javaConverter.registerOrModifToClient(clientFromApi), id);
        return ResponseEntity.status(OK).body(response);
    }

    @DeleteMapping(value = ENTITY_PATH+"/delete/{id}", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> supprimerClient(@PathVariable("id") Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.status(NO_CONTENT).body("Client supprimé avec succès");
    }

    @GetMapping(value = ENTITY_PATH+"/get/all/page/{page}/size/{size}", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<Client>> allClient(@PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Client> all = clientService.tousLesClients(page, size);
        return ResponseEntity.status(OK).body(all);
    }

}
