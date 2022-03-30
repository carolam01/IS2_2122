package es.unican.is2.impuestoCirculacionCommon;

import static org.junit.Assert.*;
import org.junit.Test;
import es.unican.is2.impuestoCirculacionCommon.Motocicleta;
import java.time.LocalDate;

public class MotocicletaTest {

	@Test
	public void testConstructorgetCilindrada() throws DatoNoValido {
		//casos validos
		Motocicleta sut = new Motocicleta("1234",LocalDate.now(),49);
		assertTrue(sut.getCilindrada()==49);
		
		Motocicleta sut1 = new Motocicleta("1234",LocalDate.now(),1); //caso minimo
		assertTrue(sut1.getCilindrada()==1);
		
		Motocicleta sut2 = new Motocicleta("1234",LocalDate.now(),3000); //caso grande(no hay un maximo)
		assertTrue(sut2.getCilindrada()==3000);
		
		//casos no validos
		try {
			Motocicleta sut3 = new Motocicleta("1234",LocalDate.now(),0); //caso extremo
			fail(); //tiene que entrar en el catch
		}catch(DatoNoValido e) {
			
		}

		try {
			Motocicleta sut4 = new Motocicleta("1234",LocalDate.now(),-50); //caso extremo
			fail(); //tiene que entrar en el catch
		}catch(DatoNoValido e) {
			assertTrue(e.getMessage().equals("No creado vehiculo con matricula 1234 cilindrada incorrecta -50"));
		}
		
		try {
			Motocicleta sut5 = new Motocicleta(null,LocalDate.now(),51); //si la matricula es null(con la cilindrada no deja el propio java poner null)
			fail();
		} catch (NullPointerException e){

		}
		
		try {
			Motocicleta sut5 = new Motocicleta("1234",null,51); //si la fecha de matriculacion es null(con la cilindrada no deja el propio java poner null)
			fail();
		} catch (NullPointerException e){

		}
		
	}
	
	@Test
	public void precioImpuestoTest() throws DatoNoValido {
		double precio = 0.0;
		double precio1 = 8.84;  //tarifa hasta 125
		double precio2 = 15.14; //tarifa [125-250)
		double precio3 = 30.30; //tarifa [250-500)
		double precio4 = 60.58; //tarifa [500-1000)
		double precio5 = 121.16; //tarifa[1000- +inf)
		double gratis = 0.0; //+25 años antiguedad
		
		///////////////////////Hasta 125///////////////////////////////
		Motocicleta sut11 = new Motocicleta("1234", LocalDate.now(),1);
		precio = sut11.precioImpuesto();
		assertTrue(precio == precio1);
		
		Motocicleta sut12 = new Motocicleta("1234", LocalDate.now(),50);
		precio = sut12.precioImpuesto();
		assertTrue(precio == precio1);
		
		Motocicleta sut13 = new Motocicleta("1234", LocalDate.now(),124);
		precio = sut13.precioImpuesto();
		assertTrue(precio == precio1);
		
		/////////////////////De 125 a 250//////////////////////////////
		Motocicleta sut21 = new Motocicleta("1234", LocalDate.now(),125);
		precio = sut21.precioImpuesto();
		assertTrue(precio == precio2);
		
		Motocicleta sut22 = new Motocicleta("1234", LocalDate.now(),200);
		precio = sut22.precioImpuesto();
		assertTrue(precio == precio2);
		
		Motocicleta sut23 = new Motocicleta("1234", LocalDate.now(),249);
		precio = sut23.precioImpuesto();
		assertTrue(precio == precio2);
		
		/////////////////////De 250 a 500///////////////////////////////
		Motocicleta sut31 = new Motocicleta("1234", LocalDate.now(),250);
		precio = sut31.precioImpuesto();
		assertTrue(precio == precio3);
		
		Motocicleta sut32 = new Motocicleta("1234", LocalDate.now(),350);
		precio = sut32.precioImpuesto();
		assertTrue(precio == precio3);
		
		Motocicleta sut33 = new Motocicleta("1234", LocalDate.now(),499);
		precio = sut33.precioImpuesto();
		assertTrue(precio == precio3);
		
		////////////////////De 500 a 1000///////////////////////////////
		Motocicleta sut41 = new Motocicleta("1234", LocalDate.now(),500);
		precio = sut41.precioImpuesto();
		assertTrue(precio == precio4);
		
		Motocicleta sut42 = new Motocicleta("1234", LocalDate.now(),750);
		precio = sut42.precioImpuesto();
		assertTrue(precio == precio4);
		
		Motocicleta sut43 = new Motocicleta("1234", LocalDate.now(),999);
		precio = sut43.precioImpuesto();
		assertTrue(precio == precio4);
		
		///////////////////De 1000 en adelante///////////////////////////
		Motocicleta sut51 = new Motocicleta("1234", LocalDate.now(),1000);
		precio = sut51.precioImpuesto();
		assertTrue(precio == precio5);
		
		Motocicleta sut52 = new Motocicleta("1234", LocalDate.now(),1748);
		precio = sut52.precioImpuesto();
		assertTrue(precio == precio5);
		
		//////////////////////Mas de 25 años de antiguedad////////////////////
		Motocicleta sut61 = new Motocicleta("1234", LocalDate.now().minusYears(25),1748);
		precio = sut61.precioImpuesto();
		assertTrue(precio == gratis);
		
		Motocicleta sut62 = new Motocicleta("1234", LocalDate.now().minusYears(26),500);
		precio = sut62.precioImpuesto();
		assertTrue(precio == gratis);
		
	}
}
