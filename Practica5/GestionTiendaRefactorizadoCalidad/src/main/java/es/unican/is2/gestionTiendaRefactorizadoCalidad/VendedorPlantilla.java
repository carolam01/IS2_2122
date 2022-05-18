package es.unican.is2.gestionTiendaRefactorizadoCalidad;

/**
 * 
 * Tipo de vendedor que engloba los que pertenecen a la plantilla
 *
 */
//WMC = 2
//WMCn = 2/2 = 1
//CCog = 0
public class VendedorPlantilla extends Vendedor {

	private final double comision; //comision variante por pertenecer a la plantilla,redefinida en cada tipo de vendedor en plantilla(junior,senior)
	//WMC = 1
	//CCog = 0	
	public VendedorPlantilla(String nombre, String id, String dni, double comisionV) {
		super(nombre, id, dni);
		comision=comisionV;
	}
	//WMC = 1
	//CCog = 0
	@Override
	public void anhadeVenta(double importe) {
		totalVentasMes += importe + importe*comision;
	}

}