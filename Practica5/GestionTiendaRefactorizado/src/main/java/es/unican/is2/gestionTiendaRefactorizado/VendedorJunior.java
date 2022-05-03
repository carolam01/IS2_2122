package es.unican.is2.gestionTiendaRefactorizado;

public class VendedorJunior extends Vendedor{
	
	private static final TipoVendedor TIPO_VENDEDOR_JUNIOR = TipoVendedor.JUNIOR;
	private static final double COMISION_JUNIOR = 0.5;

	public VendedorJunior(String nombre, String id, String dni) {
		super(nombre, id, dni, TIPO_VENDEDOR_JUNIOR, COMISION_JUNIOR);
	}
	
}
