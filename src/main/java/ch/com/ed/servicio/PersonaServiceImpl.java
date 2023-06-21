package ch.com.ed.servicio;

import java.util.List;
import ch.com.ed.dao.PersonaDao;
import ch.com.ed.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaDao personaDao;
    
    //para encriptar la contrasena
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        // Encriptar la contraseña antes de guardarla
        String contrasenaEncriptada = passwordEncoder.encode(persona.getContrasena());
        persona.setContrasena(contrasenaEncriptada);
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
}
