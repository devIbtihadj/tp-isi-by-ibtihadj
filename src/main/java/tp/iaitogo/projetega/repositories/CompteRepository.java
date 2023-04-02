package tp.iaitogo.projetega.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tp.iaitogo.projetega.entities.Compte;

import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    @Query("SELECT c FROM Compte c WHERE c.numeroCompte=:X")
    Optional<Compte> findByNumeroCompte(@Param("X") String numCompte);
}
