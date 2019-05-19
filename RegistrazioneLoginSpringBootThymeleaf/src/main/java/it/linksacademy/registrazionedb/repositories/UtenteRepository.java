package it.linksacademy.registrazionedb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.linksacademy.registrazionedb.models.Utente;

@Repository
//estendendo CrudRepository abbiamo le operazioni CRUD già implementate
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

	
	@Query(value = "SELECT Utente.id_utente FROM Utente WHERE Utente.email = :emailbyform", nativeQuery = true)
	Integer findIdByEmail(@Param("emailbyform") String emailbyform);
	
}
