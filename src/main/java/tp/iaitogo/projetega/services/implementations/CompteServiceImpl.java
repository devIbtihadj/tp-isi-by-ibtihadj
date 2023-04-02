package tp.iaitogo.projetega.services.implementations;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tp.iaitogo.projetega.entities.Compte;
import tp.iaitogo.projetega.exceptions.CompteNotFoundException;
import tp.iaitogo.projetega.exceptions.SoldeInsuffissantException;
import tp.iaitogo.projetega.repositories.CompteRepository;
import tp.iaitogo.projetega.services.CompteService;
import tp.iaitogo.projetega.utils.FunctionsUtils;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;


@Service
@Transactional
public class CompteServiceImpl implements CompteService {

    private final CompteRepository compteRepository;

    @Autowired
    public CompteServiceImpl(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public Compte creerCompte(Compte compte) {
        compte.setDateCreation(LocalDate.now());
        compte.setNumeroCompte(FunctionsUtils.generateNumeroCompte(compte));
        compte.setSolde(0.0);
        return compteRepository.save(compte);
    }

    @Override
    public Compte modifierCompte(Compte compte, Long id) {
        Optional<Compte> optionalCompte = compteRepository.findById(id);
        if(optionalCompte.isEmpty()){
            throw new CompteNotFoundException("Aucun compte n'existe avec l'id "+id);
        }else {
            compte.setId(id);
            return compteRepository.save(compte);
        }
    }

    @Override
    public void supprimerCompte(Long id) {
        Optional<Compte> optionalCompte = compteRepository.findById(id);
        if(optionalCompte.isEmpty()){
            throw new CompteNotFoundException("Aucun compte n'existe avec l'id "+id);
        }else {
            compteRepository.deleteById(id);
        }
    }

    @Override
    public Compte getCompte(Long id) {
        Optional<Compte> optionalCompte = compteRepository.findById(id);
        if(optionalCompte.isEmpty()){
            throw new CompteNotFoundException("Aucun compte n'existe avec l'id "+id);
        }else {
            return optionalCompte.get();
        }
    }

    @Override
    public Page<Compte> tousLesComptes(int page, int size) {
        return compteRepository.findAll(PageRequest.of(page - 1, size, Sort.by("id").descending()));
    }

    @Override
    public Compte versement(String numCompte, long montant) {
        Optional<Compte> optionalCompte = compteRepository.findByNumeroCompte(numCompte);
        if(optionalCompte.isEmpty()){
            throw new CompteNotFoundException("Aucun compte n'existe avec le numéro de compte "+numCompte);
        }else {
            optionalCompte.get().setSolde(optionalCompte.get().getSolde() + montant);
            return compteRepository.save(optionalCompte.get());
        }
    }

    @Override
    public Compte retrait(String numCompte, long montant) {
        Optional<Compte> optionalCompte = compteRepository.findByNumeroCompte(numCompte);
        if(optionalCompte.isEmpty()){
            throw new CompteNotFoundException("Aucun compte n'existe avec le numéro de compte "+numCompte);
        }else {
            if(optionalCompte.get().getSolde() >= montant){
                optionalCompte.get().setSolde(optionalCompte.get().getSolde() + montant);
                return compteRepository.save(optionalCompte.get());
            }else {
                throw new SoldeInsuffissantException("Solde insuffisant sur ce compte pour effectuer l'opération");
            }
        }
    }

    @Override
    @Transactional
    public void virement(String numCompteDonneur, String numCompteReceveur, long montant) {
        retrait(numCompteDonneur, montant);
        versement(numCompteReceveur, montant);
    }




}
