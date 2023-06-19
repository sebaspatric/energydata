package ch.com.ed.dao;

import ch.com.ed.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VueloRepositorio extends JpaRepository<Cliente, Long> {

}
