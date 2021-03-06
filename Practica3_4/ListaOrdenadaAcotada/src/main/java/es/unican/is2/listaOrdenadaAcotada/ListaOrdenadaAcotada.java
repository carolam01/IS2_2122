package es.unican.is2.listaOrdenadaAcotada;

public class ListaOrdenadaAcotada<E extends Comparable<E>> implements IListaOrdenadaAcotada<E> {

	public final static int MAX_POR_OMISION = 10;
	
	private E[] lista;
	private int ultimo;

	/**
	 * Constructor que indica la capacidad maxima de la lista
	 * 
	 * @param max Capacidad maxima de la lista
	 */
	public ListaOrdenadaAcotada(int max) {
		lista = (E[]) new Comparable[max];
		ultimo = -1;
	}

	/**
	 * Constructor que crea la lista con capacidad igual a maxPorOmision
	 */
	public ListaOrdenadaAcotada() {
		this(MAX_POR_OMISION);
	}

	public E get(int indice) {
		return lista[indice];
	}

	public void add(E elemento) {
		//(solucion 1) no dejar a?adir nulos
		if(elemento == null) {
			throw new NullPointerException();
		}
		// compruebo si cabe
		if (ultimo == lista.length -1) {
			throw new IllegalStateException();
		}
		// busca el lugar donde debe insertarse
		int indice = 0;
		if (ultimo != -1) {
			while (indice <= ultimo && elemento.compareTo(lista[indice]) > 0) {
				indice++;
			}
		}
		// desplaza elementos hacia adelante
		//(solucion 2, cuando hay que a?adir elementos en medio ultimo e indice valen lo mismo
		//y no se desplazar?n los elementos, restando 1 a indice se arregla)
		for (int i = ultimo; i > indice-1; i--) {
			lista[i + 1] = lista[i];
		}
		// inserta el elemento
		lista[indice] = elemento;
		ultimo++;

	}

	public E remove(int indice) {
		if (indice > ultimo) {
			throw new IndexOutOfBoundsException();
		}
		E borrado = lista[indice];
		// desplaza elementos hacia atras
		for (int i = indice + 1; i <= ultimo; i++) {
			lista[i - 1] = lista[i];
		}
		//(Solucion 3) El problema es que para el caso en el que indice es 0 y solo hay un elemento
		//el valor de i ser? mayor que el de ?ltimo y no se actualiza el valor. Con este caso especial se soluciona.
		if (ultimo == 0) {
			lista[indice] = null;
		}
		// actualiza ultimo y retorna el elemento borrado
		ultimo--;
		return borrado;
	}

	public int size() {
		return ultimo + 1;
	}

	public void clear() {
		for(int i=0; i <= ultimo;i++) { //(solucion 4) poner todos los elementos de la lista a null
			lista[i] = null;
		}
		ultimo = -1;

	}

}
