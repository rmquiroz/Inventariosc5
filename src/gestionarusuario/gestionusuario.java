package gestionarusuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import usuarios.usuario;
import utilerias.postgresql;

public class gestionusuario {

	public String obtenerusuario(usuario usu){
		
		usuario usuario=null;
		String nivel=null;
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	try {
	
		con = postgresql.getConexion();
				
				String sql="select * from usuarios where usuario=? and pass=?";
				
				pst=con.prepareStatement(sql);
				
				pst.setString(1,usu.getUsuario());
				pst.setString(2,usu.getPass());
				
				rs = pst.executeQuery();
				
				while(rs.next()){
					usuario=new usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
					nivel=rs.getString(6);
					
				}
				con.close();
								
	} catch (Exception e) {
		System.out.println("error en obtener usuario");
	}
		
		return(nivel);
	}
}