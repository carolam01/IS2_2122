package es.unican.is2.gestionTienda;


public class VendedorEnPlantilla extends Vendedor {
	
	private TipoVendedor tipo;
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	//CC = 1
	public VendedorEnPlantilla(String nombre, String id, String dni, TipoVendedor tipo) {
		super(nombre, id);
		this.tipo = tipo;
		this.dni=dni;
	}
	//CC = 1
	public TipoVendedor tipo() {
		return tipo;
	}
	//CC = 1
	public String getDni() {
		return dni;
	}
	//CC = 2 
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof VendedorEnPlantilla)) 
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));
	}
}
