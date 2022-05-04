package es.unican.is2.gestionTienda;

//WMC = 4
//WMCn = 4/3 = 1.33
//CCog = 2
public class vendedorEnPracticas extends Vendedor {
	
	private String dni;
	
	/**
	 * Retorna un nuevo vendedor en prácticas
	 * @param nombre
	 * @param dni
	 */
	//CC = 1
	//CCog = 0
	public vendedorEnPracticas(String nombre, String id, String dni) {
		super(nombre, id);
		this.dni= dni;
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
		if (!(obj instanceof vendedorEnPracticas))  //CC = 1	CCog = 1
			return false;
		vendedorEnPracticas v = (vendedorEnPracticas) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni())); //CC = 1 CCog = 2
	}
}
