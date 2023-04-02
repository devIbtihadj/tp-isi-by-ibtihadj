package tp.iaitogo.projetega.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.iaitogo.projetega.entities.Compte;
import tp.iaitogo.projetega.handlers.MyCustomExceptionsHandler;
import tp.iaitogo.projetega.request.AddOrModifCompteRequest;
import tp.iaitogo.projetega.request.VersementRequest;
import tp.iaitogo.projetega.request.VirementRequest;
import tp.iaitogo.projetega.services.CompteService;
import tp.iaitogo.projetega.utils.JavaConverter;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CompteController extends MyCustomExceptionsHandler {

    private final String ENTITY_PATH = "compte";

    private final CompteService compteService;
    private final JavaConverter javaConverter;

    @Autowired
    public CompteController(CompteService compteService, JavaConverter javaConverter) {
        this.compteService = compteService;
        this.javaConverter = javaConverter;
    }

    @PostMapping(value = ENTITY_PATH+"/create", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Compte> creerCompte(@RequestBody AddOrModifCompteRequest compteFromApi) {
        Compte response = compteService.creerCompte(javaConverter.registerOrModifToCompte(compteFromApi));
        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping(value = ENTITY_PATH+"/get/{id}", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Compte> getCompte(@PathVariable("id") Long id) {
        Compte response = compteService.getCompte(id);
        return ResponseEntity.status(OK).body(response);
    }

    @PutMapping(value = ENTITY_PATH+"/update/{id}", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Compte> modifierCompte(@RequestBody AddOrModifCompteRequest compteFromApi, @PathVariable("id") Long id) {
        Compte response = compteService.modifierCompte(javaConverter.registerOrModifToCompte(compteFromApi), id);
        return ResponseEntity.status(OK).body(response);
    }

    @DeleteMapping(value = ENTITY_PATH+"/delete/{id}", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> supprimerCompte(@PathVariable("id") Long id) {
        compteService.supprimerCompte(id);
        return ResponseEntity.status(NO_CONTENT).body("Compte supprimé avec succès");
    }

    @GetMapping(value = ENTITY_PATH+"/get/all/page/{page}/size/{size}", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Page<Compte>> allCompte(@PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Compte> all = compteService.tousLesComptes(page, size);
        return ResponseEntity.status(OK).body(all);
    }

    @GetMapping(value = ENTITY_PATH+"/versement", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Compte> versement(@RequestBody VersementRequest versementRequest) {
        Compte response = compteService.versement(versementRequest.getNumeroCompte(), versementRequest.getMontant());
        return ResponseEntity.status(OK).body(response);
    }

    @GetMapping(value = ENTITY_PATH+"/retrait", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Compte> retrait(@RequestBody VersementRequest versementRequest) {
        Compte all = compteService.retrait(versementRequest.getNumeroCompte(), versementRequest.getMontant());
        return ResponseEntity.status(OK).body(all);
    }

    @GetMapping(value = ENTITY_PATH+"/virement", produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> virement(@RequestBody VirementRequest virementRequest) {
        compteService.virement(virementRequest.getNumCompteDonneur(), virementRequest.getNumCompteReceveur(), virementRequest.getMontant());
        return ResponseEntity.status(OK).body("Virement effectuer avec succès");
    }
}
