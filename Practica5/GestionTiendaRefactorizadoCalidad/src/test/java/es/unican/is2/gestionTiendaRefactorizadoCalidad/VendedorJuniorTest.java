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
		assertEquals("1",sutJunior.getId());
		assertEquals("11111111A",sutJunior.getDni());
		assertEquals("Ana",sutJunior.getNombre());
	}

	@Test
	public void testAnhadeVenta() {
		
		sutJunior.anhadeVenta(200);
		assertEquals(201,sutJunior.getTotalVentas(), 0); //aplica comision 0.5%
		
		sutJunior.anhadeVenta(300);
		assertEquals(502.5,sutJunior.getTotalVentas(), 0);
		
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutJunior.asignaTotalVentas(2000);
		assertEquals(2000,sutJunior.getTotalVentas(),  0);	
		sutJunior.asignaTotalVentas(4000);
		assertEquals(4000,sutJunior.getTotalVentas(),  0);	
		sutJunior.asignaTotalVentas(0);
		assertEquals(0,sutJunior.getTotalVentas(),  0);
		
	}

	
	@Test
	public void testEquals() {
		VendedorJunior igualJunior = new VendedorJunior("Ana", "1", "11111111A");
		VendedorJunior distintoIdJunior = new VendedorJunior("Ana", "2", "11111111A");
		VendedorJunior distintoDNIJunior = new VendedorJunior("Ana", "1", "222222222A");
		
		assertEquals(true,sutJunior.equals(igualJunior));
		assertNotEquals(sutJunior.equals(distintoIdJunior),true);
		assertNotEquals(sutJunior.equals(distintoDNIJunior),true);
				
	}
	
	
	
}
