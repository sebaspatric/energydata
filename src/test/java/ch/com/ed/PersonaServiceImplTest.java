/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch.com.ed;

/**
 *
 * @author diurno
 */
import ch.com.ed.dao.PersonaDao;
import ch.com.ed.domain.Persona;
import ch.com.ed.servicio.PersonaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonaServiceImplTest {

    @Mock
    private PersonaDao personaDao;

    @InjectMocks
    private PersonaServiceImpl personaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listarPersonas_ReturnListOfPersonas() {
        // Arrange
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona());
        personas.add(new Persona());
        personas.add(new Persona());
        personas.add(new Persona());
        personas.add(new Persona());
        when(personaDao.findAll()).thenReturn(personas);

        // Act
        List<Persona> result = personaService.listarPersonas();

        // Assert
        assertEquals(personas.size(), result.size());
        assertTrue(result.containsAll(personas));
    }

         // Encriptar la contraseña antes de guardarla

   /*
    @Test
    public void guardar_ValidPersona_PersonaSaved() {
        // Arrange
        Persona persona = new Persona();
        persona.setContrasena("123");

        // Act
        personaService.guardar(persona);

        // Assert
        verify(personaDao, times(1)).save(persona);
    }

    */
    
    @Test
    public void eliminar_ValidPersona_PersonaDeleted() {
        // Arrange
        Persona persona = new Persona();

        // Act
        personaService.eliminar(persona);

        // Assert
        verify(personaDao, times(1)).delete(persona);
    }

    @Test
    public void encontrarPersona_ExistingPersona_ReturnPersona() {
        // Arrange
        Persona persona = new Persona();
        persona.setIdPersona(1L);
        when(personaDao.findById(persona.getIdPersona())).thenReturn(java.util.Optional.of(persona));

        // Act
        Persona result = personaService.encontrarPersona(persona);

        // Assert
        assertNotNull(result);
        assertEquals(persona, result);
    }
}
