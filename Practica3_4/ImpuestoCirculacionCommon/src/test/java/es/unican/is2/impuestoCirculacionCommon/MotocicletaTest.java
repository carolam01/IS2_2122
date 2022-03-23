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
	}
	
}
