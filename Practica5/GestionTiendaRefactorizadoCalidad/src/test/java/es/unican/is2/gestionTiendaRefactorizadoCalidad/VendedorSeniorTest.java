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
		assertEquals( "2",sutSenior.getId());
		assertEquals("222222222A",sutSenior.getDni() );
		assertEquals("Pepe",sutSenior.getNombre());
		
	}

	@Test
	public void testAnhadeVenta() {
			
		sutSenior.anhadeVenta(200);
		assertEquals(202,sutSenior.getTotalVentas(),  0); //aplica comision 1%
		
		sutSenior.anhadeVenta(300);
		assertEquals(505,sutSenior.getTotalVentas(),  0);
		
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutSenior.asignaTotalVentas(4500);
		assertEquals( 4500,sutSenior.getTotalVentas(), 0);		
		sutSenior.asignaTotalVentas(4000);
		assertEquals(4000,sutSenior.getTotalVentas(),  0);
		sutSenior.asignaTotalVentas(0);
		assertEquals(0,sutSenior.getTotalVentas(),  0);	
		
	}

	
	@Test
	public void testEquals() {
		
		VendedorSenior igualSenior = new VendedorSenior("Pepe", "2", "222222222A");
		VendedorSenior distintoIdSenior = new VendedorSenior("Pepe", "3", "222222222A");
		VendedorSenior distintoDNISenior = new VendedorSenior("Pepe", "2", "33333333A");
		
		assertEquals(true,sutSenior.equals(igualSenior));
		assertNotEquals(sutSenior.equals(distintoIdSenior),true);
		assertNotEquals(sutSenior.equals(distintoDNISenior),true);
		
	}
	
	
	
}
