package es.unican.is2.gestionTiendaRefactorizado;


public class VendedorEnPlantilla extends Vendedor {
	
	private TipoVendedor tipoVendedor;
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipoVendedor
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni, TipoVendedor tipoVendedor) {
		super(nombre, id);
		this.tipoVendedor = tipoVendedor;
		this.dni=dni;
	}
	
	public TipoVendedor tipo() {
		return tipoVendedor;
	}
	
	public String getDni() {
		return dni;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof VendedorEnPlantilla)) 
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));
	}
}
