package ch.com.ed.web;

import ch.com.ed.domain.Cliente;
import ch.com.ed.servicio.ClienteServicioImpl;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ClienteControlador {

    @Autowired
    ClienteServicioImpl clienteservicio;

    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes() {
        return clienteservicio.obtenerTodo();
    }

    //@RequestMapping(value = "/guardar",
    //        produces = "application/json",
    //        method = RequestMethod.POST)
    @PostMapping("/guardar")
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
        Cliente nuevo_cliente = clienteservicio.guardar(cliente);
        return new ResponseEntity<>(nuevo_cliente, HttpStatus.CREATED);
        //return ResponseEntity.ok(nuevo_cliente);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> obtenerClienteId(@PathVariable long id) {
        Cliente clientePorId = clienteservicio.obtenerPorId(id);
        return ResponseEntity.ok(clientePorId);
    }

    @RequestMapping(
            value = "/cliente/{id}",
            produces = "application/json",
            method = {RequestMethod.GET, RequestMethod.PUT})
    //@PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable long id, @RequestBody Cliente cliente) {
        Cliente clientePorId = clienteservicio.obtenerPorId(id);
        clientePorId.setNombre(cliente.getNombre());
        clientePorId.setApellido(cliente.getApellido());
        clientePorId.setEmail(cliente.getEmail());
        clientePorId.setTelefono(cliente.getTelefono());

        Cliente cliente_actualizado = clienteservicio.guardar(clientePorId);
        //return new ResponseEntity<>(cliente_actualizado, HttpStatus.CREATED);
        return ResponseEntity.ok(cliente_actualizado);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarCliente(@PathVariable long id) {
        this.clienteservicio.eliminar(id);

        HashMap<String, Boolean> estadoClienteEliminado = new HashMap<>();
        estadoClienteEliminado.put("eliminado", true);
        return ResponseEntity.ok(estadoClienteEliminado);
    }

}
