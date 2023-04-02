package tp.iaitogo.projetega.entities.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sexe {
    MASCULIN("M"), FEMININ("F");

    private final String sexe;
}
