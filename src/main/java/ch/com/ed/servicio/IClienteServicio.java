package ch.com.ed.servicio;

import ch.com.ed.domain.Cliente;
import java.util.List;


public interface IClienteServicio{

	public List<Cliente> obtenerTodo();
	
	public Cliente guardar(Cliente cliente);
	
	public Cliente obtenerPorId(long id);
	
	public void eliminar(long id);
}
