package es.unican.is2.gestionTiendaRefactorizadoCalidad;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.gestionTiendaRefactorizadoCalidad.VendedorJunior;


public class VendedorJuniorTest {
	
	private static VendedorJunior sutJunior;

	
	@Before
	public void setUp(){
		sutJunior = new VendedorJunior ("Ana", "1", "11111111A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sutJunior.getId(), "1");
		assertEquals(sutJunior.getDni(), "11111111A");
		assertEquals(sutJunior.getNombre(), "Ana");
	}

	@Test
	public void testAnhadeVenta() {
		
		sutJunior.anhadeVenta(200);
		assertEquals(sutJunior.getTotalVentas(), 201, 0); //aplica comision 0.5%
		
		sutJunior.anhadeVenta(300);
		assertEquals(sutJunior.getTotalVentas(), 502.5, 0);
		
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutJunior.asignaTotalVentas(2000);
		assertEquals(sutJunior.getTotalVentas(), 2000, 0);	
		sutJunior.asignaTotalVentas(4000);
		assertEquals(sutJunior.getTotalVentas(), 4000, 0);	
		sutJunior.asignaTotalVentas(0);
		assertEquals(sutJunior.getTotalVentas(), 0, 0);
		
	}

	
	@Test
	public void testEquals() {
		VendedorJunior igualJunior = new VendedorJunior("Ana", "1", "11111111A");
		VendedorJunior distintoIdJunior = new VendedorJunior("Ana", "2", "11111111A");
		VendedorJunior distintoDNIJunior = new VendedorJunior("Ana", "1", "222222222A");
		
		assertTrue(sutJunior.equals(igualJunior));
		assertFalse(sutJunior.equals(distintoIdJunior));
		assertFalse(sutJunior.equals(distintoDNIJunior));
				
	}
	
	
	
}
