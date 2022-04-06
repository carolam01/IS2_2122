package es.unican.is2.listaOrdenadaAcotada;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IListaOrdenadaAcotadaTest {


	private ListaOrdenadaAcotada<Integer> sut;
	public final static int MAX = 4;

	@Before
	public void setUp() {
		sut = new ListaOrdenadaAcotada<Integer>(MAX);
	}

	@Test
	public void testConstructor() {
		assertTrue(sut.get(0)==null); //lista sin elementos
		assertTrue(sut.size()==0);
	}

	@Test
	public void testAddYGet() {
		//casos no valido
		/*try {
			sut.add(null);
			fail("Debería haberse lanzado NullPointerException");  //(Error 1)No deberia dejar añadir elementos nulos 
		}catch(NullPointerException e){

		}*/
		try {

			sut.add(2);
			assertTrue(sut.get(0)==2);
			assertTrue(sut.size()==1);

			sut.add(8);
			assertTrue(sut.get(1)==8);
			assertTrue(sut.size()==2);

			sut.add(4); 
			//compruebo que se ha cambiado el orden ya que es menor que 8
			//compruebo que no se ha modificado los valores menores al elemento añadido
			assertTrue(sut.get(0)==2);
			assertTrue(sut.get(1)==4);
			try {
				assertTrue(sut.get(2)==8); //Error(2) al añadir un valor menor en vez de ponerse el ultimo se pone a null
			}catch(NullPointerException e) {
				fail("No deberia lanzarse NullPointerException");
			}

			System.out.println("elemento 2 de la lista" + sut.get(2));

			assertTrue(sut.size()==3);

			sut.add(7);
			assertTrue(sut.get(0)==2);
			assertTrue(sut.get(1)==4);
			assertTrue(sut.get(3)==7);
			assertTrue(sut.get(4)==8);

			assertTrue(sut.size()==4);

			//caso no valido, no deja añadir ya que se ha definido que el tamaño maximo sea 4
			sut.add(55);
			fail("Debería haberse lanzado IllegalStateException");
			
		}catch (IllegalStateException e) {
		}


	}

	@Test
	public void removeTest() {
		fail("Not yet implemented");
	}

	@Test
	public void sizeTest() {
		fail("Not yet implemented");
	}

	@Test
	public void clearTest() {
		fail("Not yet implemented");
	}
}
