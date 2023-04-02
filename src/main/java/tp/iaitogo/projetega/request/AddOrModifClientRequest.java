package tp.iaitogo.projetega.request;

import lombok.Getter;
import tp.iaitogo.projetega.entities.enums.Sexe;

import java.time.LocalDate;

@Getter
public class AddOrModifClientRequest {

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private Sexe sexe;

    private  String adresse;

    private String numeroTel;

    private String courriel;

    private String nationalite;

}
