package es.unican.is2.impuestoCirculacionCommon;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class TurismoTest {
	
	@Test
	//Casos Validos
	public void turismoTest() {
		try {
			Turismo sut1 = new Turismo("1234", LocalDate.now(), 40);
		} catch(DatoNoValido e) {
			fail();
		}
		try {
			Turismo sut2 = new Turismo("2345", LocalDate.now().plusDays(2), 50);
		} catch(DatoNoValido e) {
			fail();
		}
		//Casos no Validos
		try {
			Turismo sut3 = new Turismo("5616", LocalDate.now().minusDays(20), -1);
			fail();
		} catch(DatoNoValido e) {
			
		}
		try {
			Turismo sut4 = new Turismo(null, LocalDate.now().minusDays(1), -1);
			fail();
		} catch (DatoNoValido e) {
			
		}
	}
	@Test
	public void precioImpuestoTest() throws DatoNoValido {
		Turismo sut1 = new Turismo("1234", LocalDate.now().plusDays(1), 1);
		Turismo sut2 = new Turismo("1321", LocalDate.now().plusDays(4), 7.90);
		Turismo sut3 = new Turismo("ABBA1235", LocalDate.now().minusDays(1), 8.50);
		Turismo sut4 = new Turismo("542DDS", LocalDate.now().plusDays(10), 11.95);
		Turismo sut5 = new Turismo("569931", LocalDate.now().plusDays(14), 12.03);
		Turismo sut6 = new Turismo("65456ADEF", LocalDate.now().minusDays(39), 15.98);
		Turismo sut7 = new Turismo("552FRFRE", LocalDate.now().minusDays(41), 16.41);
		Turismo sut8 = new Turismo("52369ACF", LocalDate.now(), 19.85);
		Turismo sut9 = new Turismo("23954KOP", LocalDate.now().plusDays(5), 74.23);
		//Casos Válidos pues los casos no validos ya están cubiertos con el constructor
		Double precio = 0.0;
		
		precio = sut1.precioImpuesto();
		assertTrue(precio.toString(), precio == 25.24);
		precio = sut2.precioImpuesto();
		assertTrue(precio.toString(), precio == 25.24);	
		precio = sut3.precioImpuesto();
		assertTrue(precio.toString(), precio == 68.16);	
		precio = sut4.precioImpuesto();
		assertTrue(precio.toString(), precio == 68.16);	
		precio = sut5.precioImpuesto();
		assertTrue(precio.toString(), precio == 143.88);	
		precio = sut6.precioImpuesto();
		assertTrue(precio.toString(), precio == 143.88);	
		precio = sut7.precioImpuesto();
		assertTrue(precio.toString(), precio == 179.22);	
		precio = sut8.precioImpuesto();
		assertTrue(precio.toString(), precio == 179.22);	
		precio = sut9.precioImpuesto();
		assertTrue(precio.toString(), precio == 224);	
	}
}
