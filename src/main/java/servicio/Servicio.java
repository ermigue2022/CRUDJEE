package servicio;

import java.util.List;

import modelo.Conexion;
import modelo.Persona;

public class Servicio {
	
	public List<Persona> obtenerTodo(){
		Conexion conexion = new Conexion();
		return conexion.obtenerTodo();
	}
	
	public Persona obtenerPersona(int id) {
		Conexion conexion = new Conexion();
		return conexion.obtenerPersona(id);
	}
	
	public void insertar(Persona p) {
		Conexion conexion = new Conexion();
		conexion.insertar(p);
		
	}
	public void actualizar (Persona p) {
		Conexion conexion = new Conexion();
		conexion.actualizar(p);
		
	}
	public void eliminar (int id) {
		Conexion conexion = new Conexion();
		conexion.eliminar(id);
		
	}

	
	
	public static void main(String[] args) {
		Servicio servicio = new Servicio();
		servicio.obtenerTodo();
	}

}
