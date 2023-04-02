package tp.iaitogo.projetega.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tp.iaitogo.projetega.entities.enums.Sexe;
import tp.iaitogo.projetega.request.AddOrModifClientRequest;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
@Entity
public class Client implements Serializable {

    /**
     * Classe créée le 30/03/2023
     **/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private Sexe sexe;

    private  String adresse;

    private String numeroTel;

    @Email(message = "Veuillez renseigner une adresse email valide")
    private String courriel;

    private String nationalite;


    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Compte> comptes;


}
