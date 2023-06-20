package ch.com.ed.dao;

import java.util.List;
import ch.com.ed.domain.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VueloDao extends JpaRepository<Vuelo, Long> {
    // Métodos personalizados si los necesitas

    //vuelo ordenado porr consumo
    @Query("SELECT v FROM Vuelo v ORDER BY v.consumoUltimos100 DESC")
    List<Vuelo> obtenerVuelosOrdenadosPorConsumoUltimos100();

    //pasajeros ordenados por codigo
    @Query("SELECT v FROM Vuelo v ORDER BY v.destino.codigo DESC, v.consumoUltimos100 DESC")
    List<Vuelo> obtenerVuelosOrdenadosPorPax();

    //aeropuerrto ordenado por carga
    @Query("SELECT v FROM Vuelo v ORDER BY v.destino.PAX_TOTAL DESC, v.destino.codigo")
    List<Vuelo> obtenerVuelosOrdenadosPorPaxSum();

    //aeropuerrto ordenado por carga
    @Query("SELECT v FROM Vuelo v ORDER BY v.consumoUltimos100 DESC, v.destino.codigo")
    List<Vuelo> obtenerVuelosOrdenadosPorConsumo();

    @Query("SELECT v.origen, COUNT(v) FROM Vuelo v GROUP BY v.origen")
    List<Object[]> countByOrigen();

    /*
    -- agrupar por aeropuerto_destino
    
    SELECT aeropuerto.AEROPUERTO AS aeropuerto_destino, AVG(vuelo.consumo_ultimos_100) AS PROM_combustible
    FROM vuelo
    JOIN aeropuerto WHERE vuelo.destino_codigo = aeropuerto.codigo
    GROUP BY aeropuerto_destino;
     */
    @Query("SELECT v.destino.AEROPUERTO, COUNT(v), v.destino.latitud, v.destino.longitud, v.destino.PAX_TOTAL, AVG(v.consumoUltimos100), AVG(v.cargaPasajeros)  "
            + "FROM Vuelo v GROUP BY v.destino "
            + "ORDER BY v.destino.AEROPUERTO ASC")
    List<Object[]> obtenerVuelosAgrupadosPorDestino();

    // ordenado por pasajero de aeropuerto descendente
    @Query("SELECT v.destino.AEROPUERTO, COUNT(v), v.destino.latitud, v.destino.longitud, v.destino.PAX_TOTAL, AVG(v.consumoUltimos100), AVG(v.cargaPasajeros)  "
            + "FROM Vuelo v GROUP BY v.destino "
            + "ORDER BY v.destino.PAX_TOTAL DESC")
    List<Object[]> obtenerVuelosAgrupadosPorDestinoOBPA();

    // ordenar por promedio consumo utimos100 desc
    @Query("SELECT v.destino.AEROPUERTO, COUNT(v), v.destino.latitud, v.destino.longitud, v.destino.PAX_TOTAL, AVG(v.consumoUltimos100), AVG(v.cargaPasajeros)  FROM Vuelo v GROUP BY v.destino ORDER BY AVG(v.consumoUltimos100) DESC")
    List<Object[]> obtenerVuelosAgrupadosPorDestinoOBC();

    //ordenar por carga de pasajeros
    @Query("SELECT v.destino.AEROPUERTO, COUNT(v), v.destino.latitud, v.destino.longitud, v.destino.PAX_TOTAL, AVG(v.consumoUltimos100), AVG(v.cargaPasajeros)  FROM Vuelo v GROUP BY v.destino ORDER BY AVG(v.cargaPasajeros) DESC")
    List<Object[]> obtenerVuelosAgrupadosPorDestinoOBP();

    //@Query("SELECT a.codigo AS aeropuertoDestino, SUM(v.consumoUltimos100) AS sumaCombustible " +
    //        "FROM Vuelo v " +
    //        "JOIN v.destino aeropuerto " +
    //        "GROUP BY aeropuertoDestino")
    //List<Object[]> obtenerConsumoCombustiblePorAeropuertoDestino();
}
