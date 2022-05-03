package es.unican.is2.gestionTiendaRefactorizado;

public class VendedorPracticas extends Vendedor {

	private static final TipoVendedor TIPO_VENDEDOR_PRACTICAS = TipoVendedor.PRACTICAS;
	private static final double COMISION_PRACTICAS = 0;

	public VendedorPracticas(String nombre, String id, String dni) {
		super(nombre, id, dni, TIPO_VENDEDOR_PRACTICAS, COMISION_PRACTICAS);
	}
}
