package ch.com.ed.dao;

import ch.com.ed.domain.Aeropuerto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AeropuertoDao extends JpaRepository<Aeropuerto, Long> {
    // Métodos personalizados si los necesitas
}

