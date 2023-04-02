package tp.iaitogo.projetega.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.iaitogo.projetega.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
