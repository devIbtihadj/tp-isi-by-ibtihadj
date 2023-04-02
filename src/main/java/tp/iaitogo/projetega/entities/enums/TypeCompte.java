package tp.iaitogo.projetega.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeCompte {
    EPARGNE("EPARGNE"), COURANT("COURANT");

    private final String type;

}
