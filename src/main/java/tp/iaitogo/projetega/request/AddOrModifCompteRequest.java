package tp.iaitogo.projetega.request;

import lombok.Getter;
import tp.iaitogo.projetega.entities.enums.TypeCompte;


@Getter
public class AddOrModifCompteRequest {

    private TypeCompte typeCompte;

    private Long client_id;
}
