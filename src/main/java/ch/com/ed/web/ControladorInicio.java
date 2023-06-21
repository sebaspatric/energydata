package ch.com.ed.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import ch.com.ed.domain.Persona;
import ch.com.ed.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private PersonaService personaService;
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        var personas = personaService.listarPersonas();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        model.addAttribute("personas", personas);
        return "index";
    }
    
    @GetMapping("/control")             //recuperar el usuario que hizo login
    public String control(Model model, @AuthenticationPrincipal User user){
        var personas = personaService.listarPersonas();
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        model.addAttribute("personas", personas);
        return "control";
    }
    // muestra planilla para agregar
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        return "modificar";
    }
    @GetMapping("/agregarlog")
    public String agregarlog(Persona persona){
        return "modificarlog";
    }
    
    @GetMapping("/contacto")
    public String contacto(Persona persona){
        return "contacto";
    }
    
    @GetMapping("/registrov")
    public String registrov(Persona persona){
        return "registrov";
    }    
    
    // guarda la persona CRUD
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        // Encriptar la contraseña antes de guardarla
        //String contrasenaEncriptada = passwordEncoder.encode(persona.getContrasena());
        //persona.setContrasena(contrasenaEncriptada);
        personaService.guardar(persona);
        return "redirect:/control";
    }
    
    // nuevo registro
    @PostMapping("/registroguardar")
    public String registroguardar(@Valid Persona persona, Errors errores){
        //verifica errores
        if(errores.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/control";
    }
    
    // muestra el formulario para editar
    @GetMapping("/editar/{idPersona}")
        // en spring no es necesario inicializar el objeto ni hacerse con el prametro id.. lo asocia automatico
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar"; // vista sirve para agregar y editar
    }
    
    //@GetMapping("/eliminar/{idPersona}")
    // se paasa el id como query parametro en la vista
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/control";
    }
}
