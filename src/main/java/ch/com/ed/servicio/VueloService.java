package ch.com.ed.servicio;

import java.util.List;
import ch.com.ed.domain.Vuelo;

public interface VueloService {
    
    public List<Vuelo> listarVuelos();
    
    public void guardar(Vuelo vuelo);
    
    public void eliminar(Vuelo vuelo);
    
    public Vuelo encontrarVuelo(Vuelo vuelo);
    
    public List<Vuelo> obtenerVuelosOrdenadosPorConsumoUltimos100();
    
    public List<Vuelo> obtenerVuelosOrdenadosPorPax();
    
    public List<Vuelo> obtenerVuelosOrdenadosPorPaxSum();
    
    public List<Vuelo> obtenerVuelosOrdenadosPorConsumo();
    
    public List<Object[]> obtenerVuelosAgrupadosPorOrigen();
    
    public List<Object[]> obtenerVuelosAgrupadosPorDestino();

    public List<Object[]> obtenerVuelosAgrupadosPorDestinoOBPA();

    public List<Object[]> obtenerVuelosAgrupadosPorDestinoOBC();

    public List<Object[]> obtenerVuelosAgrupadosPorDestinoOBP();

    //public List<Vuelo> obtenerVuelosAgrupadosPorOrigen();

    
   // public List<Object[]> obtenerConsumoCombustiblePorAeropuertoDestino();
}
