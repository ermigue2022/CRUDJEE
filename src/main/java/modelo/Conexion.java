package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conexion implements Transacciones{
	
	private final String url="jdbc:sqlite:C:\\eclipse-workspace\\CRUDJEE\\base.db";
	
	
	private Connection conectar(){
		Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (Exception e) {
           e.printStackTrace();
        }
		return conn;
    
	}

	@Override
	public List<Persona> obtenerTodo() {
		Connection _conn=null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			_conn=DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		List<Persona> listaPersonas = new ArrayList<Persona>();
		String sql="SELECT * FROM persona";
		
		try {
			
			Statement stmt = _conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Persona persona = new Persona(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("cedula"),rs.getString("direccion"));
				System.out.println(rs.getString("nombre"));
				listaPersonas.add(persona);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
				_conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return listaPersonas;
	}

	@Override
	public Persona obtenerPersona(int id) {
		Connection _conn=null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			_conn=DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*---------------------------------------------------------- una forma de hacerlo
		Persona persona = new Persona();
		String sql ="SELECT * from persona where id=?";
		
		try {
			PreparedStatement stmt = _conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			persona.setId(rs.getInt("id"));
			persona.setNombre(rs.getString("nombre"));
			persona.setApellido(rs.getString("apellido"));
			persona.setCedula(rs.getString("cedula"));
			persona.setDireccion(rs.getString("direccion"));
			
			
		*--------------------------------------------------------------------------------------*/
		
		Persona p = null;
		String sql="SELECT * FROM persona WHERE id=?";
		
		try {
			
			PreparedStatement stmt = _conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery(); // dos formas de hacerlo, incluso mezclando 
			p = new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getString(3), rs.getString(4), rs.getString("direccion"));
			//p = new Persona(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("cedula"),rs.getString("direccion"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			_conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(p.getApellido());
		return p;
	}

	@Override
	public void insertar(Persona p) { //MARCADORES DE POSICION ?
		String sql="INSERT INTO persona(nombre,apellido,cedula,direccion) VALUES(?,?,?,?,?)";
		
		try {
			Connection conn = this.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getNombre());
			pstmt.setString(2, p.getApellido());
			pstmt.setString(3, p.getCedula());
			pstmt.setString(4, p.getDireccion());
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void actualizar(Persona p) {
		String sql="UPDATE persona SET nombre=?,apellido=?,cedula=?,direccion=? WHERE id=?";
		
		try {
			Connection conn = this.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getNombre());
			pstmt.setString(2, p.getApellido());
			pstmt.setString(3, p.getCedula());
			pstmt.setString(4, p.getDireccion());
			pstmt.setInt(5, p.getId());
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminar(int id) {
		String sql = "DELETE FROM persona WHERE id=?";
		try {
			Connection conn = this.conectar();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		Conexion conexion = new Conexion();
		//conexion.obtenerTodo();
		conexion.obtenerPersona(5);

	}

}