package es.unican.is2.gestionTiendaRefactorizado;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


/**
 * Clase que representa una tienda con un conjunto de vendedores.
 * Gestiona las ventas realizadas y las comisiones asignadas a cada
 * vendedor. Los datos de la tienda se almacenan en un fichero de texto
 * que se pasa como parámetro al crear la tienda
 */
public class Tienda {

	private LinkedList<Vendedor> listaVendedores = new LinkedList<Vendedor>();
	private String direccion;
	private String nombre;

	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * 
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) {
		this.datos = datos;
	}

	/**
	 * Retorna la dirección de la tienda
	 * @return Dirección de la tienda
	 */
	public String direccion() {
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> vendedores() {
		return listaVendedores;
	}

	/**
	 * Añade un nuevo vendedor a la tienda
	 * 
	 * @param nuevoVendedor
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya había un vendedor con el mismo id
	 */
	public boolean anhadeNuevoVendedor(Vendedor nuevoVendedor) throws IOException {
		String id = nuevoVendedor.getId();
		Vendedor v = buscaVendedor(id);
		if (v != null) {
			return false;
		}
		listaVendedores.add(nuevoVendedor);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo id se pasa como parámetro
	 * 
	 * @param id
	 * @return true si se elimina el vendedor 
	 *         false si no existe ningún vendedor con el id indicado
	 */
	public boolean eliminaVendedor(String id) throws IOException {
		Vendedor v = buscaVendedor(id);
		if (v == null) {
			return false;
		}
		listaVendedores.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Añade una venta a un vendedor
	 * @param id Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se añade la venta 
	 *         false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws IOException {
		Vendedor v = buscaVendedor(id);
		if (v == null) {
			return false;
		}
		v.anhadeVenta(importe);
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese id o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) {
		for (Vendedor v : listaVendedores) {
			if (v.getId().equals(id)) {
				return v;
			}
		}
		return null;
	}
	
	/**
	 * Método que lee el fichero para crear los vendedores de la tienda
	 */
	public void leeFichero() {

		listaVendedores = new LinkedList<Vendedor>();
		Scanner in = null;
		try {
			// abre el fichero
			in = new Scanner(new FileReader(datos));
			// configura el formato de números
			in.useLocale(Locale.ENGLISH);
			nombre = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor ven = null;
			// lee los vendedores senior
			while (in.hasNext() && !in.next().equals("Junior")) { 
				anhadeVendedor(in,TipoVendedor.SENIOR,ven);
			}
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals("Prácticas")) { 
				anhadeVendedor(in,TipoVendedor.JUNIOR,ven);
			}
			while (in.hasNext()) {
				in.next();
				anhadeVendedor(in,TipoVendedor.PRACTICAS,ven);
			}
		} catch (FileNotFoundException e) {
		} finally {
			if (in != null) {
				in.close();
			}
		} // try
	}
	
	/**
	 * Método que anhade el vendeodr a la lista según se va leyendo el fichero
	 * @param in puntero de lectura del fichero
	 * @param tipo tipo de vendedor con el que se va a trabajar
	 * @param vendedor constructor del vendedor
	 */
	private void anhadeVendedor (Scanner in,TipoVendedor tipo, Vendedor vendedor) {
		String nombre = in.next();
		in.next();
		String idIn = in.next();
		in.next();
		String dni= in.next();
		in.next();
		double totalVentas = in.nextDouble();
		switch(tipo) {
			case SENIOR:
				vendedor = new VendedorSenior(nombre, idIn, dni);
				break;
			case JUNIOR:
				vendedor = new VendedorJunior(nombre, idIn, dni);
				break;
			case PRACTICAS:
				vendedor = new VendedorPracticas(nombre, idIn, dni);
				break;
			default:
		}

		vendedor.asignaTotalVentas(totalVentas);
		listaVendedores.add(vendedor);
	}


	/**
	 * Método que actualiza el fichero datosTienda.txt 
	 * con los datos actualizados de los vendedores
	 */
	private void vuelcaDatos() throws IOException {
		PrintWriter out = null;
		List<Vendedor> listaSenior = new LinkedList<Vendedor>();
		List<Vendedor> listaJunior = new LinkedList<Vendedor>();
		List<Vendedor> listaPracticas = new LinkedList<Vendedor>();

		//añadir vendedores a listas dependiendo el tipo
		for (Vendedor v : listaVendedores) {
			if (v instanceof VendedorPracticas) {
				listaPracticas.add(v);
			} else if (v instanceof VendedorJunior) {		//modificado el if
				listaJunior.add(v);
			}else {
				listaSenior.add(v);
			}
		}

		try {

			out = new PrintWriter(new FileWriter(datos));

			out.println(nombre);
			out.println(direccion);
			out.println();

			out.println("Senior");
			imprimirInfoLista(out,listaSenior);

			out.println();
			out.println("Junior");
			imprimirInfoLista(out,listaJunior);

			out.println();
			out.println("Prácticas");
			imprimirInfoLista(out,listaPracticas);


		} finally {
			if (out != null)
				out.close();
		}
	}

	/**
	 * Método que va actualizando el fichero
	 * @param salida puntero para escribir sobre el fichero
	 * @param lista lista que se va a escribir
	 */
	private void imprimirInfoLista(PrintWriter salida,List<Vendedor> lista) {

		for (Vendedor v : lista) {
			salida.println("  Nombre: " + v.getNombre() + " Id: " + v.getId() + " DNI: "+ v.getDni()+" TotalVentasMes: "
					+ v.getTotalVentas());
		}
	}

}
