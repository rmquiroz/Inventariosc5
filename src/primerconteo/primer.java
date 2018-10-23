package primerconteo;

public class primer {
	
	private String codigo;
	private String cantidad;
	private String marbete;

	public primer(String codigo, String cantidad,String marbete ) {

		this.codigo = codigo;
		this.cantidad = cantidad;
		this.marbete = marbete;

	}
	
	public primer() {
		// TODO Auto-generated constructor stub
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	public String getcantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getmarbete() {
		return marbete;
	}

	public void setmarbete(String marbete) {
		this.marbete = marbete;
	}
	
}
