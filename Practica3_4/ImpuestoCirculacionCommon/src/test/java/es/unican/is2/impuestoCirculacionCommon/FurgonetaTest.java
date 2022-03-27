package es.unican.is2.impuestoCirculacionCommon;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class FurgonetaTest {
	
	@Test
	public void furgonetaTest() {
		try {
			Furgoneta sut1 = new Furgoneta("1234", LocalDate.now(), 21, true);
		} catch(DatoNoValido e) {
			fail();
		}
		try {
			Furgoneta sut2 = new Furgoneta("2345", LocalDate.now().plusDays(2), 50, false);
		} catch(DatoNoValido e) {
			fail();
		}
		//Casos no Validos
		try {
			Furgoneta sut3 = new Furgoneta("5616", LocalDate.now().minusDays(20), -0.99, true);
			fail();
		} catch(DatoNoValido e) {
			
		}
	}
	@Test
	public void precioImpuestoTest() throws DatoNoValido {
		Furgoneta sut1 = new Furgoneta("1234", LocalDate.now().plusDays(1), 1, false);
		Furgoneta sut2 = new Furgoneta("1321", LocalDate.now().plusDays(4), 7.90, true);
		Furgoneta sut3 = new Furgoneta("ABBA1235", LocalDate.now().minusDays(1), 8.50, true);
		Furgoneta sut4 = new Furgoneta("542DDS", LocalDate.now().plusDays(10), 11.95, false);
		Furgoneta sut5 = new Furgoneta("569931", LocalDate.now().plusDays(14), 12.03, false);
		Furgoneta sut6 = new Furgoneta("65456ADEF", LocalDate.now().minusDays(39), 15.98, true);
		Furgoneta sut7 = new Furgoneta("552FRFRE", LocalDate.now().minusDays(41), 16.41, true);
		Furgoneta sut8 = new Furgoneta("52369ACF", LocalDate.now(), 19.85, false);
		Furgoneta sut9 = new Furgoneta("23954KOP", LocalDate.now().plusDays(5), 74.23, true);
		Furgoneta sut10 = new Furgoneta("4589263V", LocalDate.now().minusDays(28), 20.21, false);
		//Casos Válidos pues los casos no validos ya están cubiertos con el constructor
		Double precio = 0.0;
		
		precio = sut1.precioImpuesto();
		System.out.print(precio);
		assertTrue(precio.toString(), precio == 25.24);
		precio = sut2.precioImpuesto();
		assertTrue(precio.toString(), precio == (25.24*0.8));	
		precio = sut3.precioImpuesto();
		assertTrue(precio.toString(), precio == (68.16*0.8));	
		precio = sut4.precioImpuesto();
		assertTrue(precio.toString(), precio == 68.16);	
		precio = sut5.precioImpuesto();
		assertTrue(precio.toString(), precio == 143.88);	
		precio = sut6.precioImpuesto();
		assertTrue(precio.toString(), precio == (143.88*0.8));	
		precio = sut7.precioImpuesto();
		assertTrue(precio.toString(), precio == (179.22*0.8));	
		precio = sut8.precioImpuesto();
		assertTrue(precio.toString(), precio == 179.22);	
		precio = sut9.precioImpuesto();
		assertTrue(precio.toString(), precio == (224*0.8));
		precio = sut10.precioImpuesto();
		assertTrue(precio.toString(), precio == 224);
	}
}
