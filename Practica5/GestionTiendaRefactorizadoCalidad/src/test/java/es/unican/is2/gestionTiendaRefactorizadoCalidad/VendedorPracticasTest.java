package es.unican.is2.gestionTiendaRefactorizadoCalidad;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.gestionTiendaRefactorizadoCalidad.VendedorPracticas;


public class VendedorPracticasTest {
	
	private static VendedorPracticas sut;

	@Before
	public void setUp(){
		sut = new VendedorPracticas("Ana", "1", "11111111A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals("1",sut.getId());
		assertEquals("11111111A",sut.getDni());
		assertEquals("Ana",sut.getNombre());
		assertEquals(true,sut.getTotalVentas()==0.0);
	}
	
	@Test
	public void testSetTotalVentas() {
		sut.asignaTotalVentas(100);
		assertEquals(true,sut.getTotalVentas()==100.0);
		
		sut.asignaTotalVentas(230);
		assertEquals(true,sut.getTotalVentas()==230.0);
		
		sut.asignaTotalVentas(0);
		assertEquals(true,sut.getTotalVentas()==0.0);
	}

	@Test
	public void testAnhadeVenta() {
		sut.anhadeVenta(200);
		assertEquals(true,sut.getTotalVentas() == 200.0);
		
		sut.anhadeVenta(300);
		assertEquals(true,sut.getTotalVentas() == 500.0);	
		
		sut.anhadeVenta(0);
		assertEquals(true,sut.getTotalVentas() == 500.0);
		
		
	}
	
	@Test
	public void testEquals() {
		VendedorPracticas igual = new VendedorPracticas("Ana", "1", "11111111A");
		VendedorPracticas distintoId = new VendedorPracticas("Ana", "2", "11111111A");
		VendedorPracticas distintoNombre = new VendedorPracticas("Pepe", "1", "222222222A");
		
		assertEquals(true,sut.equals(igual));
		assertNotEquals(sut.equals(distintoId),true);
		assertNotEquals(sut.equals(distintoNombre),true);
	}
	
	
	
}
