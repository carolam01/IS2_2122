package es.unican.is2.impuestoCirculacionCommon;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class TurismoTest {
	
	@SuppressWarnings("unused")
	@Test
	//Casos Validos
	public void turismoTest() {
		try {
			Turismo sut1 = new Turismo("1234", LocalDate.now(), 40);
			assertTrue(sut1.getPotencia()==40);
		} catch(DatoNoValido e) {
			fail();
		}
		try {
			Turismo sut2 = new Turismo("2345", LocalDate.now().plusDays(2), 50);
			assertTrue(sut2.getPotencia()==50);
		} catch(DatoNoValido e) {
			fail();
		}
		//Casos no Validos
		try {
			Turismo sut3 = new Turismo("5616", LocalDate.now().minusDays(20), -1);
			assertTrue(sut3.getPotencia()==-1);
			fail();
		} catch(DatoNoValido e) {
			
		}
		try {
			Turismo sut4 = new Turismo(null, LocalDate.now().minusDays(1), 1);
			assertTrue(sut4.getPotencia()==-1);
			fail();
		} catch (DatoNoValido e) {
			
		} catch (NullPointerException e) {
			
		}
		try {
			Turismo sut2 = new Turismo("2345", null, 12.5);
			assertTrue(sut2.getPotencia()==12.5);
			fail();
		} catch(DatoNoValido e) {

		}catch(NullPointerException e) {
			
		}
	}
	@Test
	public void precioImpuestoTest() throws DatoNoValido {
		//En primer lugar listamos los posibles precios de las furgonetas
		double precio1 = 25.24;  //tarifa hasta 8
		double precio2 = 68.16; //tarifa [8-12)
		double precio3 = 143.88; //tarifa [12-16)
		double precio4 = 179.22; //tarifa [16-20)
		double precio5 = 224; //tarifa[20- +inf)
		double gratis = 0.0; //+25 años antiguedad
		Turismo sut1 = new Turismo("1234", LocalDate.now().plusDays(1), 1);
		Turismo sut2 = new Turismo("1321", LocalDate.now().plusDays(4), 7.90);
		Turismo sut3 = new Turismo("ABBA1235", LocalDate.now().minusDays(1), 8.50);
		Turismo sut4 = new Turismo("542DDS", LocalDate.now().plusDays(10), 11.95);
		Turismo sut5 = new Turismo("569931", LocalDate.now().plusDays(14), 12.03);
		Turismo sut6 = new Turismo("65456ADEF", LocalDate.now().minusDays(39), 15.98);
		Turismo sut7 = new Turismo("552FRFRE", LocalDate.now().minusDays(41), 16.41);
		Turismo sut8 = new Turismo("52369ACF", LocalDate.now(), 19.85);
		Turismo sut9 = new Turismo("23954KOP", LocalDate.now().plusDays(5), 74.23);
		Turismo sut10 = new Turismo("51385LK", LocalDate.now().minusYears(25), 114);
		Turismo sut11 = new Turismo("12569PO", LocalDate.now().minusYears(26), 12.85);
		//Casos Válidos pues los casos no validos ya están cubiertos con el constructor
		Double precio = 0.0;
		
		///////////////////////Hasta 8///////////////////////////////
		precio = sut1.precioImpuesto();
		assertTrue(precio == precio1);
		precio = sut2.precioImpuesto();
		assertTrue(precio == precio1);
		///////////////////////[8-12)///////////////////////////////
		precio = sut3.precioImpuesto();
		assertTrue(precio == precio2);	
		precio = sut4.precioImpuesto();
		assertTrue(precio == precio2);
		///////////////////////[12-16)///////////////////////////////
		precio = sut5.precioImpuesto();
		assertTrue(precio == precio3);	
		precio = sut6.precioImpuesto();
		assertTrue(precio == precio3);
		///////////////////////[16-20)///////////////////////////////
		precio = sut7.precioImpuesto();
		assertTrue(precio == precio4);	
		precio = sut8.precioImpuesto();
		assertTrue(precio == precio4);
		///////////////////////[20-inf)///////////////////////////////
		precio = sut9.precioImpuesto();
		assertTrue(precio == precio5);
		//Antiguedad justo a 25 precio = precio 5
		precio = sut10.precioImpuesto();
		assertTrue(precio == precio5);
		//Mayor a 25 precio = gratis
		precio = sut11.precioImpuesto();
		assertTrue(precio == gratis);
	}
}
