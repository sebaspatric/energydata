package ch.com.ed.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import ch.com.ed.domain.Vuelo;
import ch.com.ed.servicio.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/vuelos")
@Slf4j
public class ControladorVuelo {

    @Autowired
    private VueloService vueloService;

    @GetMapping("/control2")
    public String control(Model model, @AuthenticationPrincipal User user) {
        var vuelos = vueloService.listarVuelos();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        model.addAttribute("vuelos", vuelos);
        return "control2";
    }

    @GetMapping("/vuelos")
    public String obtenerVuelosOrdenadosPorConsumo(Model model, @AuthenticationPrincipal User user) {
        var vuelos = vueloService.obtenerVuelosOrdenadosPorConsumoUltimos100();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        model.addAttribute("vuelos", vuelos);
        return "vuelos-lista";
    }
    
    @GetMapping("/control3")
    public String obtenerVuelosyaeropuerto(Model model, @AuthenticationPrincipal User user) {
        var vuelos = vueloService.listarVuelos();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        model.addAttribute("vuelos", vuelos);
        return "aeropuerto-lista";
    }   
    @GetMapping("/control4")
    public String obtenerVuelosporpasajero(Model model, @AuthenticationPrincipal User user) {
        var vuelos = vueloService.obtenerVuelosOrdenadosPorPaxSum();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        model.addAttribute("vuelos", vuelos);
        return "aeropuerto-lista";
    }  
    @GetMapping("/control5")
    public String obtenerVuelosporconsumo(Model model, @AuthenticationPrincipal User user) {
        var vuelos = vueloService.obtenerVuelosOrdenadosPorConsumo();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        model.addAttribute("vuelos", vuelos);
        return "aeropuerto-lista";
    }    
    
    
    @GetMapping("/control6")
    public String vuelosAgrupados(Model model, @AuthenticationPrincipal User user) {
        var vuelosAgrupados = vueloService.obtenerVuelosAgrupadosPorDestino();
        log.info("Ejecutando el controlador Spring MVC");
        log.info("Usuario que hizo login: " + user);
        model.addAttribute("vuelosAgrupados", vuelosAgrupados);
        return "vuelos";
    }
    
    @GetMapping("/control7")
    public String vuelosAgrupadosOBPA(Model model, @AuthenticationPrincipal User user) {
        var vuelosAgrupados = vueloService.obtenerVuelosAgrupadosPorDestinoOBPA();
        log.info("Ejecutando el controlador Spring MVC");
        log.info("Usuario que hizo login: " + user);
        model.addAttribute("vuelosAgrupados", vuelosAgrupados);
        return "vuelos";
    }
    
    @GetMapping("/control8")
    public String vuelosAgrupadosOBC(Model model, @AuthenticationPrincipal User user) {
        var vuelosAgrupados = vueloService.obtenerVuelosAgrupadosPorDestinoOBC();
        log.info("Ejecutando el controlador Spring MVC");
        log.info("Usuario que hizo login: " + user);
        model.addAttribute("vuelosAgrupados", vuelosAgrupados);
        return "vuelos";
    }
    
    @GetMapping("/control9")
    public String vuelosAgrupadosOBP(Model model, @AuthenticationPrincipal User user) {
        var vuelosAgrupados = vueloService.obtenerVuelosAgrupadosPorDestinoOBP();
        log.info("Ejecutando el controlador Spring MVC");
        log.info("Usuario que hizo login: " + user);
        model.addAttribute("vuelosAgrupados", vuelosAgrupados);
        return "vuelos";
    }    
    

    //@GetMapping("/vconsumo")
    //public String obtenerConsumoCombustible(Model model, @AuthenticationPrincipal User user) {
    //    var result = vueloService.obtenerConsumoCombustiblePorAeropuertoDestino();
    //    model.addAttribute("result", result);
    //    return "consumo_combustible";
   // }

    @GetMapping("/agregar2")
    public String agregar(Vuelo vuelo) {
        return "modificar2";
    }

    @GetMapping("/agregarlog2")
    public String agregarlog(Vuelo vuelo) {
        return "modificarlog2";
    }

    @GetMapping("/contacto2")
    public String contacto(Vuelo vuelo) {
        return "contacto2";
    }

    @GetMapping("/registrov2")
    public String registrov(Vuelo vuelo) {
        return "registrov2";
    }

    @PostMapping("/guardar2")
    public String guardar(@Valid Vuelo vuelo, Errors errores) {
        if (errores.hasErrors()) {
            return "modificar2";
        }
        vueloService.guardar(vuelo);
        return "redirect:/control2";
    }

    @PostMapping("/registroguardar2")
    public String registroguardar(@Valid Vuelo vuelo, Errors errores) {
        if (errores.hasErrors()) {
            return "modificar2";
        }
        vueloService.guardar(vuelo);
        return "redirect:/control2";
    }

    @GetMapping("/editar2/{idVuelo}")
    public String editar(Vuelo vuelo, Model model) {
        vuelo = vueloService.encontrarVuelo(vuelo);
        model.addAttribute("vuelo", vuelo);
        return "modificar2";
    }

    @GetMapping("/eliminar2")
    public String eliminar(Vuelo vuelo) {
        vueloService.eliminar(vuelo);
        return "redirect:/control2";
    }
}
