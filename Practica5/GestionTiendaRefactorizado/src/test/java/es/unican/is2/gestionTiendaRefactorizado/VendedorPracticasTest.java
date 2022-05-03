package es.unican.is2.gestionTiendaRefactorizado;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.gestionTiendaRefactorizado.VendedorPracticas;


public class VendedorPracticasTest {
	
	private static VendedorPracticas sut;

	@Before
	public void setUp(){
		sut = new VendedorPracticas("Ana", "1", "11111111A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sut.getId(), "1");
		assertEquals(sut.getNombre(), "Ana");
		assertEquals(sut.getDni(), "11111111A");
		assertTrue(sut.getTotalVentas()==0.0);
	}
	
	@Test
	public void testSetTotalVentas() {
		sut.asignaTotalVentas(100);
		assertTrue(sut.getTotalVentas()==100.0);
		
		sut.asignaTotalVentas(230);
		assertTrue(sut.getTotalVentas()==230.0);
		
		sut.asignaTotalVentas(0);
		assertTrue(sut.getTotalVentas()==0.0);
	}

	@Test
	public void testAnhadeVenta() {
		sut.anhadeVenta(200);
		assertTrue(sut.getTotalVentas() == 200.0);
		
		sut.anhadeVenta(300);
		assertTrue(sut.getTotalVentas() == 500.0);	
		
		sut.anhadeVenta(0);
		assertTrue(sut.getTotalVentas() == 500.0);
		
		
	}
	
	@Test
	public void testEquals() {
		VendedorPracticas igual = new VendedorPracticas("Ana", "1", "11111111A");
		VendedorPracticas distintoId = new VendedorPracticas("Ana", "2", "11111111A");
		VendedorPracticas distintoNombre = new VendedorPracticas("Pepe", "1", "222222222A");
		
		assertTrue(sut.equals(igual));
		assertFalse(sut.equals(distintoId));
		assertFalse(sut.equals(distintoNombre));
	}
	
	
	
}
