package es.unican.is2.gestionTiendaRefactorizado;

/**
 * 
 * Vendedor Senior que pertenece a los vendedores en plantilla
 *
 */

public class VendedorSenior extends VendedorPlantilla{

	private static final double COMISION_SENIOR = 0.01; //1%

	public VendedorSenior(String nombre, String id, String dni) {
		super(nombre, id, dni, COMISION_SENIOR);
	}

}
