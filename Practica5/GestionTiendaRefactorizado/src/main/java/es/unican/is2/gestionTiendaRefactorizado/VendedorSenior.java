package es.unican.is2.gestionTiendaRefactorizado;

public class VendedorSenior extends Vendedor{

	private static final TipoVendedor TIPO_VENDEDOR_SENIOR = TipoVendedor.SENIOR;
	private static final double COMISION_SENIOR = 1;

	public VendedorSenior(String nombre, String id, String dni) {
		super(nombre, id, dni, TIPO_VENDEDOR_SENIOR, COMISION_SENIOR);
	}

}
