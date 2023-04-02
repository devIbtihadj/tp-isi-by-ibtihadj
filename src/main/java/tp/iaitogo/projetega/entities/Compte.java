package tp.iaitogo.projetega.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import tp.iaitogo.projetega.entities.enums.TypeCompte;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comptes")
@Entity
public class Compte implements Serializable {

    /**
     * Classe créée le 30/03/2023
     **/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String numeroCompte;

    private TypeCompte typeCompte;

    private LocalDate dateCreation;

    private Double solde;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


}
