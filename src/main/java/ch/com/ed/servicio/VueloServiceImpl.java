package ch.com.ed.servicio;

import java.util.List;
import ch.com.ed.dao.VueloDao;
import ch.com.ed.domain.Vuelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VueloServiceImpl implements VueloService {

    @Autowired
    private VueloDao vueloDao;

    @Override
    @Transactional(readOnly = true)
    public List<Vuelo> listarVuelos() {
        return (List<Vuelo>) vueloDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vuelo> obtenerVuelosOrdenadosPorConsumoUltimos100() {
        return (List<Vuelo>) vueloDao.obtenerVuelosOrdenadosPorConsumoUltimos100();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Vuelo> obtenerVuelosOrdenadosPorPax() {
        return (List<Vuelo>) vueloDao.obtenerVuelosOrdenadosPorPax();
    }
    
    //@Override
    //@Transactional(readOnly = true)
    //public List<Object[]> obtenerVuelosAgrupadosPorOrigen() {
    //    return vueloDao.countByOrigen();
    //}
    //@Override
    //@Transactional(readOnly = true)
    //public List<Object[]> obtenerConsumoCombustiblePorAeropuertoDestino() {
    //    return (List<Object[]>) vueloDao.obtenerConsumoCombustiblePorAeropuertoDestino();
    //}

    @Override
    @Transactional
    public void guardar(Vuelo vuelo) {
        vueloDao.save(vuelo);
    }

    @Override
    @Transactional
    public void eliminar(Vuelo vuelo) {
        vueloDao.delete(vuelo);
    }

    @Override
    @Transactional(readOnly = true)
    public Vuelo encontrarVuelo(Vuelo vuelo) {
        return vueloDao.findById(vuelo.getIdVuelo()).orElse(null);
    }

    @Override
    public List<Vuelo> obtenerVuelosOrdenadosPorPaxSum() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return (List<Vuelo>) vueloDao.obtenerVuelosOrdenadosPorPaxSum();
    }

    @Override
    public List<Vuelo> obtenerVuelosOrdenadosPorConsumo() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return (List<Vuelo>) vueloDao.obtenerVuelosOrdenadosPorConsumo();        
    }

    @Override
    public List<Object[]> obtenerVuelosAgrupadosPorOrigen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Object[]> obtenerVuelosAgrupadosPorDestino() {
        return vueloDao.obtenerVuelosAgrupadosPorDestino();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Object[]> obtenerVuelosAgrupadosPorDestinoOBPA() {
        return vueloDao.obtenerVuelosAgrupadosPorDestinoOBPA();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Object[]> obtenerVuelosAgrupadosPorDestinoOBC() {
        return vueloDao.obtenerVuelosAgrupadosPorDestinoOBC();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Object[]> obtenerVuelosAgrupadosPorDestinoOBP() {
        return vueloDao.obtenerVuelosAgrupadosPorDestinoOBP();
    }
}
