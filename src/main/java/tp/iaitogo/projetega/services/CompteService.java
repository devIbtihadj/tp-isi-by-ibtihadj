package tp.iaitogo.projetega.services;

import org.springframework.data.domain.Page;
import tp.iaitogo.projetega.entities.Compte;

import java.util.HashMap;


public interface CompteService {

    Compte creerCompte(Compte compte);

    Compte modifierCompte(Compte compte, Long id);

    void supprimerCompte(Long id);

    Compte getCompte(Long id);

    HashMap<String, Object> tousLesComptes(int page, int size);

    Compte versement(String numCompte, long montant);

    Compte retrait(String numCompte, long montant);

    void virement(String numCompteDonneur,String numCompteReceveur, long montant);

}
