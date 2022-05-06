package es.unican.is2.gestionTiendaRefactorizado;

/**
 * 
 * Tipo de vendedor que engloba los que pertenecen a la plantilla
 *
 */

public class VendedorPlantilla extends Vendedor {
	
	private final double COMISION; //comision variante por pertenecer a la plantilla,redefinida en cada tipo de vendedor en plantilla(junior,senior)

	public VendedorPlantilla(String nombre, String id, String dni, double comision) {
		super(nombre, id, dni);
		COMISION=comision;
	}

	@Override
	public void anhadeVenta(double importe) {
		totalVentasMes += importe + importe*COMISION;
	}
	
}
