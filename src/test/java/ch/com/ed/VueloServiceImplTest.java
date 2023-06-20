/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch.com.ed;

/**
 *
 * @author diurno
 */
import ch.com.ed.dao.VueloDao;
import ch.com.ed.domain.Vuelo;
import ch.com.ed.servicio.VueloServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VueloServiceImplTest {

    //crea una instancia temporal para las pruebas
    @Mock
    private VueloDao vueloDao;

    @InjectMocks
    private VueloServiceImpl vueloService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listarVuelos_ReturnListOfVuelos() {
        // Arrange
        List<Vuelo> vuelos = new ArrayList<>();
        //vuelos.add(new Vuelo());
        vuelos.add(new Vuelo());
        vuelos.add(new Vuelo());
        vuelos.add(new Vuelo());
        vuelos.add(new Vuelo());
        vuelos.add(new Vuelo());
        vuelos.add(new Vuelo());
        vuelos.add(new Vuelo());
        vuelos.add(new Vuelo());
        vuelos.add(new Vuelo());
        when(vueloDao.findAll()).thenReturn(vuelos);

        // Act
        List<Vuelo> result = vueloService.listarVuelos();

        // Assert
        assertEquals(vuelos.size(), result.size());
        assertTrue(result.containsAll(vuelos));
    }

    @Test
    public void obtenerVuelosOrdenadosPorConsumoUltimos100_ReturnListOfVuelos() {
        // Arrange
        List<Vuelo> vuelos = new ArrayList<>();
        vuelos.add(new Vuelo());
        vuelos.add(new Vuelo());
        when(vueloDao.obtenerVuelosOrdenadosPorConsumoUltimos100()).thenReturn(vuelos);

        // Act
        List<Vuelo> result = vueloService.obtenerVuelosOrdenadosPorConsumoUltimos100();

        // Assert
        assertEquals(vuelos.size(), result.size());
        assertTrue(result.containsAll(vuelos));
    }

    @Test
    public void guardar_ValidVuelo_VueloSaved() {
        // Arrange
        Vuelo vuelo = new Vuelo();

        // Act
        vueloService.guardar(vuelo);

        // Assert
        verify(vueloDao, times(1)).save(vuelo);
    }

    @Test
    public void eliminar_ValidVuelo_VueloDeleted() {
        // Arrange
        Vuelo vuelo = new Vuelo();

        // Act
        vueloService.eliminar(vuelo);

        // Assert
        verify(vueloDao, times(1)).delete(vuelo);
    }

    @Test
    public void encontrarVuelo_ExistingVuelo_ReturnVuelo() {
        // Arrange
        Vuelo vuelo = new Vuelo();
        vuelo.setIdVuelo(1L);
        when(vueloDao.findById(vuelo.getIdVuelo())).thenReturn(java.util.Optional.of(vuelo));

        // Act
        Vuelo result = vueloService.encontrarVuelo(vuelo);

        // Assert
        assertNotNull(result);
        assertEquals(vuelo, result);
    }
    
    
    @Test
    public void obtenerVuelosOrdenadosPorConsumo_ReturnSortedByConsumo() {
        // Arrange
        List<Vuelo> vuelos = new ArrayList<>();
        Vuelo vuelo1 = new Vuelo();
        vuelo1.setConsumoUltimos100(100.0);
        Vuelo vuelo2 = new Vuelo();
        vuelo2.setConsumoUltimos100(200.0);
        vuelos.add(vuelo2);
        vuelos.add(vuelo1);
        when(vueloDao.obtenerVuelosOrdenadosPorConsumoUltimos100()).thenReturn(vuelos);

        // Act
        List<Vuelo> resultado = vueloService.obtenerVuelosOrdenadosPorConsumoUltimos100();

        // Assert
        assertEquals(vuelo1, resultado.get(1));
        assertEquals(vuelo2, resultado.get(0));
    }
}
