package es.unican.is2.impuestoCirculacionCommon;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class TurismoTest {
	
	@Test
	//Casos Validos
	public void TurismoTest() {
		try {
			Turismo t1 = new Turismo("1234", LocalDate.now(), 40);
		} catch() {
			fail();
		}
		try {
			Turismo t2 = new Turismo("2345", LocalDate.now().plusDays(2), 50);
		} catch() {
			fail();
		}
		//Casos no Validos
		try {
			Turismo t3 = new Turismo("5616", LocalDate.now().minusDays(20), -1);
			fail();
		} catch() {
	
		}
	}
}
