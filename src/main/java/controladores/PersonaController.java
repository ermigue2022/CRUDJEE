package controladores;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import modelo.Persona;
import servicio.Servicio;

//indicar que va a ser el bean que conecta directamente con la Vista XHTML
@Named
@RequestScoped
public class PersonaController {
	//aqui definiremos los métodos que se conectan directamente con la pagina web, deben de retornar NULL
	//Podemos usar los mismo nombre que en transacciones pero vamos a cambiarselo para diferenciar
	
	static List<Persona> listaPersona = new ArrayList<Persona>();
	private Persona persona;
	private Servicio crud = new Servicio();
	
	public PersonaController() { //para resetear la persona que nos faltaba del método seleccionarTodo()
		setPersona(new Persona());
	}
	
	
	public List<Persona> seleccionarTodo(){
		listaPersona.clear(); //limpiamos la lista
		listaPersona = crud.obtenerTodo(); //cargamos la lista con los elementos que obtengamos de la base de dato, posteriormente se renderizará en la pagina web
		return null;
	}
	public Persona obtenerPersona(int id) {
		this.persona = crud.obtenerPersona(id);
		return this.persona;
	}
	public String insertar() {
		crud.insertar(persona);
		persona=new Persona();
		seleccionarTodo();
		
		return null;
	}
	public String actualizar() {
		crud.actualizar(persona);
		persona=new Persona();
		seleccionarTodo();
		return null;
	}
	public String eliminar (int id) {
		crud.eliminar(id);
		persona=new Persona();//creamos una persona en blanco para resetear
		seleccionarTodo();
		return null;
	}


	
	public List<Persona> getListaPersona() {
		return listaPersona;
	}



	public void setListaPersona(List<Persona> listaPersona) {
		this.listaPersona = listaPersona;
	}



	public Persona getPersona() {
		return persona;
	}



	public void setPersona(Persona persona) {
		this.persona = persona;
	}



	public static void main(String[] args) {
		PersonaController personaController = new PersonaController();
		personaController.obtenerPersona(3);
	}

}
