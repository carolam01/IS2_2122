package es.unican.is2.gestionTiendaRefactorizado;

/**
 * 
 * Vendedor Junior que pertenece a los vendedores en plantilla
 *
 */

public class VendedorJunior extends VendedorPlantilla{
	
	private static final double COMISION_JUNIOR = 0.05; //0.5%

	public VendedorJunior(String nombre, String id, String dni) {
		super(nombre, id, dni, COMISION_JUNIOR);
	}
	
}
