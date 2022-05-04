package es.unican.is2.gestionTienda;


//WMC = 5
//WMCn = 5/4 = 1.25
//CCog = 2
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
	//CCog = 0
	public VendedorEnPlantilla(String nombre, String id, String dni, TipoVendedor tipo) {
		super(nombre, id);
		this.tipo = tipo;
		this.dni=dni;
	}
	//CC = 1
	//CCog = 0
	public TipoVendedor tipo() {
		return tipo;
	}
	//CC = 1
	//CCog = 0
	public String getDni() {
		return dni;
	}
	//CC = 2 
	//CCog = 2
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof VendedorEnPlantilla)) //CC = 1 CCog = 1
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni())); //CC = 1 CCog = 2
	}
}
