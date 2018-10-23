package usuarios;
public class usuario {

	private String usuario;
	private String nombre;
	private String apellido;
	private String pass;
	private String depto;

	public usuario(String usuario, String nombre, String apellido, String pass,
			String depto) {

		this.usuario = usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.pass = pass;
		this.depto = depto;
	}


	public usuario() {
		// TODO Auto-generated constructor stub
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

}
