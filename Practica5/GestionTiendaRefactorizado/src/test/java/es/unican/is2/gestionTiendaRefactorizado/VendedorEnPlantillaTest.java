package es.unican.is2.gestionTiendaRefactorizado;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.gestionTiendaRefactorizado.TipoVendedor;
import es.unican.is2.gestionTiendaRefactorizado.VendedorEnPlantilla;


public class VendedorEnPlantillaTest {
	
	private static VendedorEnPlantilla sutJunior;
	private static VendedorEnPlantilla sutSenior;

	
	@Before
	public void setUp(){
		sutJunior = new VendedorEnPlantilla("Ana", "1", "11111111A", TipoVendedor.JUNIOR);
		sutSenior = new VendedorEnPlantilla("Pepe", "2", "222222222A", TipoVendedor.SENIOR);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sutJunior.getId(), "1");
		assertEquals(sutJunior.getDni(), "11111111A");
		assertEquals(sutJunior.getNombre(), "Ana");
		assertEquals(sutJunior.tipo(), TipoVendedor.JUNIOR);
		assertEquals(sutSenior.tipo(), TipoVendedor.SENIOR);
		
	}

	@Test
	public void testAnhadeVenta() {
		
		sutJunior.anhadeVenta(200);
		assertEquals(sutJunior.getTotalVentas(), 200, 0);
		
		sutJunior.anhadeVenta(300);
		assertEquals(sutJunior.getTotalVentas(), 500, 0);
		
		sutSenior.anhadeVenta(300);
		assertEquals(sutSenior.getTotalVentas(), 300, 0);
		
		sutSenior.anhadeVenta(300);
		assertEquals(sutSenior.getTotalVentas(), 600, 0);
		
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutJunior.asignaTotalVentas(2000);
		assertEquals(sutJunior.getTotalVentas(), 2000, 0);	
		sutJunior.asignaTotalVentas(4000);
		assertEquals(sutJunior.getTotalVentas(), 4000, 0);	
		sutJunior.asignaTotalVentas(0);
		assertEquals(sutJunior.getTotalVentas(), 0, 0);
		
		sutSenior.asignaTotalVentas(4500);
		assertEquals(sutSenior.getTotalVentas(), 4500, 0);		
		sutSenior.asignaTotalVentas(4000);
		assertEquals(sutSenior.getTotalVentas(), 4000, 0);
		sutJunior.asignaTotalVentas(0);
		assertEquals(sutJunior.getTotalVentas(), 0, 0);	
		
	}

	
	@Test
	public void testEquals() {
		VendedorEnPlantilla igualJunior = new VendedorEnPlantilla("Ana", "1", "11111111A", TipoVendedor.JUNIOR);
		VendedorEnPlantilla distintoIdJunior = new VendedorEnPlantilla("Ana", "2", "11111111A", TipoVendedor.JUNIOR);
		VendedorEnPlantilla distintoDNIJunior = new VendedorEnPlantilla("Ana", "1", "222222222A", TipoVendedor.JUNIOR);
		
		assertTrue(sutJunior.equals(igualJunior));
		assertFalse(sutJunior.equals(distintoIdJunior));
		assertFalse(sutJunior.equals(distintoDNIJunior));
		
		
		VendedorEnPlantilla igualSenior = new VendedorEnPlantilla("Pepe", "2", "222222222A", TipoVendedor.SENIOR);
		VendedorEnPlantilla distintoIdSenior = new VendedorEnPlantilla("Pepe", "3", "222222222A", TipoVendedor.SENIOR);
		VendedorEnPlantilla distintoDNISenior = new VendedorEnPlantilla("Pepe", "2", "33333333A", TipoVendedor.SENIOR);
		
		assertTrue(sutSenior.equals(igualSenior));
		assertFalse(sutSenior.equals(distintoIdSenior));
		assertFalse(sutSenior.equals(distintoDNISenior));
		
		
		
		
	}
	
	
	
}
