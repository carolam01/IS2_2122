package es.unican.is2.listaOrdenadaAcotada;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IListaOrdenadaAcotadaTest {


	private ListaOrdenadaAcotada<Integer> sut;
	//Caso de prubea para satisfacer la coertura de sentencias del constructor
	//con el límite por defecto de Integers.
	private ListaOrdenadaAcotada<Integer> sut1;
	public final static int MAX = 4;

	@Before
	public void setUp() {
		sut1 = new ListaOrdenadaAcotada<Integer>();
		sut = new ListaOrdenadaAcotada<Integer>(MAX);
	}

	@Test
	public void testConstructor() {
		assertTrue(sut.get(0)==null); //lista sin elementos
		assertTrue(sut1.get(0)==null); //lista sin elementos
		assertTrue(sut.size()==0);
	}

	@Test
	public void testAddYGet() {
		//casos no valido
		try {
			sut.add(null);
			fail("Debería haberse lanzado NullPointerException");  //(Error 1)No deberia dejar añadir elementos nulos 
		}catch(NullPointerException e){

		}
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


			assertTrue(sut.size()==3);

			sut.add(7);
			assertTrue(sut.get(0)==2);
			assertTrue(sut.get(1)==4);
			assertTrue(sut.get(2)==7);
			assertTrue(sut.get(3)==8);

			assertTrue(sut.size()==4);

			//caso no valido, no deja añadir ya que se ha definido que el tamaño maximo sea 4
			try {
			sut.add(10);
			fail("Debería haberse lanzado IllegalStateException");
			} catch (IllegalStateException e) {
	
			}

		}catch (IllegalStateException e) {
			fail("no deberia darse este error");
		}


	}

	@Test
	public void removeTest() {
		//creo una lista para poder trabajar con ella [2,4,7,8]
		sut.add(2);
		sut.add(4);
		sut.add(7);
		sut.add(8);
		
		//casos validos
		
		//eliminar primer elemento
		assertTrue(sut.remove(0)==2);
		assertTrue(sut.get(0)== 4);
		assertTrue(sut.get(1)== 7);
		assertTrue(sut.get(2)== 8);
		assertTrue(sut.size()==3);
		//lista actual [4,7,8]
		
		//eliminar elemento intermedio
		assertTrue(sut.remove(1)==7);
		assertTrue(sut.get(0)== 4);
		assertTrue(sut.get(1)==8);
		assertTrue(sut.size()==2);
		//lista actual [4,8]
		
		//eliminar ultimo elemento
		assertTrue(sut.remove(1)==8);
		assertTrue(sut.get(0)== 4);
		assertTrue(sut.size()==1);
		//lista actual [4]
		
		//elimino ultimo elemento y lista vacia
		assertTrue(sut.remove(0)==4);
		assertTrue(sut.get(0)==null); //Error(3) error en el borrado al quedar un elemento en la lista
		assertTrue(sut.size()==0);
		//lista actual []
		
		//casos no validos
		
		//eliminar elemento de lista vacia
		try {
			sut.remove(1);
			fail("Debería haberse lanzado IndexOutOfBoundsException"); 
		}catch(IndexOutOfBoundsException e){

		}
		
		//creo una lista [1,5,6,9]
		sut.add(1);
		sut.add(5);
		sut.add(6);
		sut.add(9);
		
		//intento eliminar con un indice que sale de rango
		try {
			sut.remove(7);
			fail("Debería haberse lanzado IndexOutOfBoundsException");   
		}catch(IndexOutOfBoundsException e){

		}
		
		//intento eliminar con un indice negativo
		try {
			sut.remove(-3);
			fail("Debería haberse lanzado IndexOutOfBoundsException");  
		}catch(IndexOutOfBoundsException e){

		}
		
		
		
	}



	@Test
	public void clearTest() {
		//casos validos (todos los son)
		
		//borro lista vacia
		sut.clear();
		assertTrue(sut.get(0)==null);
		assertTrue(sut.size()==0);
		
		//creo una lista para poder trabajar con ella [2,4,6,7]
		sut.add(2);
		sut.add(4);
		sut.add(6);
		sut.add(7);
		sut.clear();
		assertTrue(sut.get(0)==null); //(Error 4)error en el clear, no se borra el primer elemento
		assertTrue(sut.size()==0);
		
	}
}
