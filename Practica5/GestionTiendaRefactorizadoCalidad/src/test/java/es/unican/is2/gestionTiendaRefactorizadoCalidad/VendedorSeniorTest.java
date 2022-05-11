package es.unican.is2.gestionTiendaRefactorizadoCalidad;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.gestionTiendaRefactorizadoCalidad.VendedorSenior;


public class VendedorSeniorTest {
	
	private static VendedorSenior sutSenior;

	
	@Before
	public void setUp(){
		sutSenior = new VendedorSenior ("Pepe", "2", "222222222A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sutSenior.getId(), "2");
		assertEquals(sutSenior.getDni(), "222222222A");
		assertEquals(sutSenior.getNombre(), "Pepe");
		
	}

	@Test
	public void testAnhadeVenta() {
			
		sutSenior.anhadeVenta(200);
		assertEquals(sutSenior.getTotalVentas(), 202, 0); //aplica comision 1%
		
		sutSenior.anhadeVenta(300);
		assertEquals(sutSenior.getTotalVentas(), 505, 0);
		
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutSenior.asignaTotalVentas(4500);
		assertEquals(sutSenior.getTotalVentas(), 4500, 0);		
		sutSenior.asignaTotalVentas(4000);
		assertEquals(sutSenior.getTotalVentas(), 4000, 0);
		sutSenior.asignaTotalVentas(0);
		assertEquals(sutSenior.getTotalVentas(), 0, 0);	
		
	}

	
	@Test
	public void testEquals() {
		
		VendedorSenior igualSenior = new VendedorSenior("Pepe", "2", "222222222A");
		VendedorSenior distintoIdSenior = new VendedorSenior("Pepe", "3", "222222222A");
		VendedorSenior distintoDNISenior = new VendedorSenior("Pepe", "2", "33333333A");
		
		assertTrue(sutSenior.equals(igualSenior));
		assertFalse(sutSenior.equals(distintoIdSenior));
		assertFalse(sutSenior.equals(distintoDNISenior));
		
		
	}
	
	
	
}
