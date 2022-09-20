package modelo;

import java.util.List;

public interface Transacciones {
	//crear métodos para realizar el CRUD
	
		public List<Persona> obtenerTodo();
		public Persona obtenerPersona(int id);
		public void insertar(Persona p);
		public void actualizar (Persona p);
		public void eliminar (int id);

}
