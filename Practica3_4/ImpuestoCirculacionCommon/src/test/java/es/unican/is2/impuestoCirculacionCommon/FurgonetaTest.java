package es.unican.is2.impuestoCirculacionCommon;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class FurgonetaTest {
	
	@Test
	public void furgonetaTest() {
		try {
			Furgoneta sut1 = new Furgoneta("1234", LocalDate.now(), 21, true);
			assertTrue(sut1.getPotencia()==21);
			assertTrue(sut1.getComercial()==true);
		} catch(DatoNoValido e) {
			fail();
		}
		try {
			Furgoneta sut2 = new Furgoneta("2345", LocalDate.now().plusDays(2), 50, false);
			assertTrue(sut2.getPotencia()==50);
			assertTrue(sut2.getComercial()==false);
		} catch(DatoNoValido e) {
			fail();
		}
		//Casos no Validos
		try {
			Furgoneta sut3 = new Furgoneta("5616", LocalDate.now().minusDays(20), -0.99, true);
			assertTrue(sut3.getPotencia()==0.99);
			assertTrue(sut3.getComercial()==true);
			fail();
		} catch(DatoNoValido e) {
			
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
		//Ahora cremaos los objetos de la clase para probarla
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
		Furgoneta sut11 = new Furgoneta("4564545LM", LocalDate.now().minusYears(26), 65.32, true);
		Furgoneta sut12 = new Furgoneta("265145JK", LocalDate.now().minusYears(25), 5.2, false);
		//Casos Válidos pues los casos no validos ya están cubiertos con el constructor
		Double precio = 0.0;
		///////////////////////Hasta 8///////////////////////////////
		precio = sut1.precioImpuesto();
		assertTrue(precio == precio1);
		//Con precio rebajado
		precio = sut2.precioImpuesto();
		assertTrue(precio == (precio1*0.8));
		///////////////////////[8-12)///////////////////////////////
		precio = sut3.precioImpuesto();
		assertTrue(precio == (precio2*0.8));
		//Con precio rebajado
		precio = sut4.precioImpuesto();
		assertTrue(precio == precio2);
		///////////////////////[12-16)///////////////////////////////
		precio = sut5.precioImpuesto();
		assertTrue(precio == precio3);	
		//Con precio rebajado
		precio = sut6.precioImpuesto();
		assertTrue(precio == (precio3*0.8));
		///////////////////////[16-20)///////////////////////////////
		precio = sut7.precioImpuesto();
		assertTrue(precio == (precio4*0.8));
		//Con precio rebajado
		precio = sut8.precioImpuesto();
		assertTrue(precio == precio4);
		///////////////////////[20-inf)///////////////////////////////
		precio = sut9.precioImpuesto();
		assertTrue(precio == (precio5*0.8));
		//Con precio rebajado
		precio = sut10.precioImpuesto();
		assertTrue(precio == precio5);
		
		//Antiguedad mayor a 25 precio = 0
		precio = sut11.precioImpuesto();
		assertTrue(precio == gratis);
		
		precio = sut12.precioImpuesto();
		assertTrue(precio == gratis);
	}
}
