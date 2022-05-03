package es.unican.is2.gestionTiendaRefactorizado;


/**
 * Vendedor de la tienda. 
 * Por cada vendedor se almacenan sus datos personales 
 * y sus datos sobre ventas y comisiones
 */
public abstract class Vendedor {
	
	private final TipoVendedor TIPO_VENDEDOR; 
	private final double COMISION;
	
	
	private String id;
	private String nombre;
	private String dni;
	
	
	// Valor total de las ventas mensuales realizadas por el vendedor
	private double totalVentasMes;
	
	public Vendedor(String nombre, String id, String dni, TipoVendedor tipoVendedor, double comision) {
		this.nombre = nombre;
		this.id = id;
		this.dni=dni;
		TIPO_VENDEDOR=tipoVendedor;
		COMISION=comision;

	}
	

	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna el id del vendedor
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Retorna el dni del vendedor
	 * @return dni
	 */
	public String getDni() {
		return dni;
	}
	
	/**
	 * Retorna el total de ventas acumuladas por el vendedor
	 * @return Total de ventas
	 */
	public double getTotalVentas() {
		return totalVentasMes;
	}
	
	/**
	 * 
	 * @return
	 */
	public TipoVendedor getTipoVendedor() {
		return TIPO_VENDEDOR;
	}
	
	/**
	 * Asigna el total de ventas acumuladas por el vendedor
	 * Se utiliza para poder cargar los datos desde fichero
	 * @param Total de ventas
	 */
	public void asignaTotalVentas(double totalVentas) {
		this.totalVentasMes = totalVentas;
	}
	
	/**
	 * Anhade una nueva venta al vendedor, actualizando su comision
	 * @param importe de la venta
	 */
	public void anhadeVenta(double importe){
		totalVentasMes += importe + importe*COMISION;
	}

	
}
