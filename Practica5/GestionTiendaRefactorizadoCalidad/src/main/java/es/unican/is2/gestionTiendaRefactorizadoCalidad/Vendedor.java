package es.unican.is2.gestionTiendaRefactorizadoCalidad;


/**
 * Vendedor de la tienda. 
 * Por cada vendedor se almacenan sus datos personales 
 * y sus datos sobre ventas y comisiones
 */
//WMC = 8
//WMCn = 8/8 = 1
//CCog = 1
public abstract class Vendedor {
	
	private String id;
	private String nombre;
	private String dni;
	
	
	// Valor total de las ventas mensuales realizadas por el vendedor
	protected double totalVentasMes; 
	//WMC = 1
	//CCog = 0	
	public Vendedor(String nombre, String id, String dni) {
		this.nombre = nombre;
		this.id = id;
		this.dni=dni;

	}
	

	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	//WMC = 1
	//CCog = 0
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna el id del vendedor
	 * @return id
	 */
	//WMC = 1
	//CCog = 0
	public String getId() {
		return id;
	}
	
	/**
	 * Retorna el dni del vendedor
	 * @return dni
	 */
	//WMC = 1
	//CCog = 0
	public String getDni() {
		return dni;
	}
	
	/**
	 * Retorna el total de ventas acumuladas por el vendedor
	 * @return Total de ventas
	 */
	//WMC = 1
	//CCog = 0
	public double getTotalVentas() {
		return totalVentasMes;
	}
	
	
	/**
	 * Asigna el total de ventas acumuladas por el vendedor
	 * Se utiliza para poder cargar los datos desde fichero
	 * @param Total de ventas
	 */
	//WMC = 1
	//CCog = 0
	public void asignaTotalVentas(double totalVentas) {
		this.totalVentasMes = totalVentas;
	}
	
	/**
	 * Anhade una nueva venta al vendedor, actualizando su comision
	 * @param importe de la venta
	 */
	//WMC = 1
	//CCog = 0
	public void anhadeVenta(double importe){
		totalVentasMes += importe ;
	}
	//WMC = 1
	//CCog = 1
	@Override
	public boolean equals(Object obj) {
		return (id.equals(((Vendedor) obj).id) &&  dni.equals(((Vendedor) obj).dni)); //WMC = 1	CCog = 1
	}

}

