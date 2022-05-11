package es.unican.is2.gestionTiendaRefactorizadoCalidad;

/**
 * 
 * Vendedor Senior que pertenece a los vendedores en plantilla
 *
 */
//WMC = 1
//WMCn = 1/1 = 1
//CCog = 0
public class VendedorSenior extends VendedorPlantilla{
	//WMC = 1
	//CCog = 0
	private static final double COMISION_SENIOR = 0.01; //1%

	public VendedorSenior(String nombre, String id, String dni) {
		super(nombre, id, dni, COMISION_SENIOR);
	}

}
