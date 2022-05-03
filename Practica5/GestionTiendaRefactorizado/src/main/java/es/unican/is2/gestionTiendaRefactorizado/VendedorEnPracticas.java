package es.unican.is2.gestionTiendaRefactorizado;


public class VendedorEnPracticas extends Vendedor {  //1.1-Poner la primera letra de la clase en may�scula
	
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en pr�cticas
	 * @param nombre
	 * @param dni
	 */
	public VendedorEnPracticas(String nombre, String id, String dni) {
		super(nombre, id);
		this.dni= dni;
	}
	
	public String getDni() {
		return dni;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof VendedorEnPracticas)) 
			return false;
		VendedorEnPracticas v = (VendedorEnPracticas) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));
	}
}
