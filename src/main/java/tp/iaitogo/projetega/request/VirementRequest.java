package tp.iaitogo.projetega.request;

import lombok.Getter;

@Getter
public class VirementRequest {

    private String numCompteDonneur;

    private String numCompteReceveur;

    private long montant;
}
