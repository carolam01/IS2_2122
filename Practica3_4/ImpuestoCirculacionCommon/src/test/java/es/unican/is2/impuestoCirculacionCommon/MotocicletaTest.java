package es.unican.is2.impuestoCirculacionCommon;

import static org.junit.Assert.*;
import org.junit.Test;
import es.unican.is2.impuestoCirculacionCommon.Motocicleta;
import java.time.LocalDate;

public class MotocicletaTest {

	@Test
	public void testConstructorgetPotencia() throws DatoNoValido {
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
		precio = sut11.precioImpuesto();
		assertTrue(precio == precio1);
		
		Motocicleta sut13 = new Motocicleta("1234", LocalDate.now(),124);
		precio = sut11.precioImpuesto();
		assertTrue(precio == precio1);
		
		/////////////////////De 125 a 250//////////////////////////////
		Motocicleta sut21 = new Motocicleta("1234", LocalDate.now(),125);
		Motocicleta sut22 = new Motocicleta("1234", LocalDate.now(),200);
		Motocicleta sut23 = new Motocicleta("1234", LocalDate.now(),249);
		
		/////////////////////De 250 a 500///////////////////////////////
		Motocicleta sut31 = new Motocicleta("1234", LocalDate.now(),250);
		Motocicleta sut32 = new Motocicleta("1234", LocalDate.now(),350);
		Motocicleta sut33 = new Motocicleta("1234", LocalDate.now(),499);
		
		////////////////////De 500 a 1000///////////////////////////////
		Motocicleta sut41 = new Motocicleta("1234", LocalDate.now(),500);
		Motocicleta sut42 = new Motocicleta("1234", LocalDate.now(),750);
		Motocicleta sut43 = new Motocicleta("1234", LocalDate.now(),999);
		
		///////////////////De 1000 en adelante///////////////////////////
		Motocicleta sut51 = new Motocicleta("1234", LocalDate.now(),1000);
		Motocicleta sut52 = new Motocicleta("1234", LocalDate.now(),1748);
		
		//////////////////////Mas de 25 años de antiguedad////////////////////
		Motocicleta sut61 = new Motocicleta("1234", LocalDate.now().minusYears(25),1748);
		Motocicleta sut62 = new Motocicleta("1234", LocalDate.now().minusYears(26),500);

	}
}
