package es.unican.is2.gestionTiendaRefactorizado;

/**
 * 
 * Vendedor Junior que pertenece a los vendedores en plantilla
 *
 */
//WMC = 1
//WMCn = 1/1 = 1
//CCog = 0
public class VendedorJunior extends VendedorPlantilla{
	//WMC = 1
	//CCog = 0
	private static final double COMISION_JUNIOR = 0.005; //0.5%

	public VendedorJunior(String nombre, String id, String dni) {
		super(nombre, id, dni, COMISION_JUNIOR);
	}

}
