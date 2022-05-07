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
//WMC = 31
//WMCn = 31/12 = 2.58
//CCog = 19
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
	//WMC = 1
	//CCog = 0
	public Tienda(String datos) {
		this.datos = datos;
	}

	/**
	 * Retorna la dirección de la tienda
	 * @return Dirección de la tienda
	 */
	//WMC = 1
	//CCog = 0
	public String direccion() {
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	//WMC = 1
	//CCog = 0
	public String nombre() {
		return nombre;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda 
	 * @return La lista de vendedores
	 */
	//WMC = 1
	//CCog = 0
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

	//WMC = 2
	//CCog = 1
	public boolean anhadeNuevoVendedor(Vendedor nuevoVendedor) throws IOException {
		String id = nuevoVendedor.getId();
		Vendedor v = buscaVendedor(id);
		if (v!= null) { //CC = 1	CCog = 1
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
	//WMC = 2
	//CCog =	1
	public boolean eliminaVendedor(String id) throws IOException {
		Vendedor v = buscaVendedor(id);
		if (v == null) { //CC = 1	CCog = 1
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
	//WMC = 2
	//CCog = 1
	public boolean anhadeVenta(String id, double importe) throws IOException {
		Vendedor v = buscaVendedor(id);
		if (v == null) { //CC = 1	CCog = 1
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
	//WMC = 3
	//CCog = 3
	public Vendedor buscaVendedor(String id) {
		for (Vendedor v : listaVendedores) { // CC = 1 CCog = 1
			if (v.getId().equals(id)) { //CC = 2	CCog = 3
				return v;
			}
		}
		return null;
	}

	/**
	 * Método que lee el fichero para crear los vendedores de la tienda
	 */
	//WMC = 7
	//CCog = 5 
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
			while (in.hasNext() && !in.next().equals("Junior")) { //CC = 2	CCog = 1
				anhadeVendedor(in,TipoVendedor.SENIOR,ven);
			}
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals("Prácticas")) { //CC = 4 CCog = 2
				anhadeVendedor(in,TipoVendedor.JUNIOR,ven);
			}
			while (in.hasNext()) { //CC = 5	CCog = 3
				in.next();
				anhadeVendedor(in,TipoVendedor.PRACTICAS,ven);
			}
		} catch (FileNotFoundException e) { //CC = 5	CCog = 4
		} finally {
			if (in != null) { //CC = 6	CCog = 5
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
	//WMC = 4
	//CCog = 1
	private void anhadeVendedor (Scanner in,TipoVendedor tipo, Vendedor vendedor) {
		String nombre = in.next();
		in.next();
		String idIn = in.next();
		in.next();
		String dni= in.next();
		in.next();
		double totalVentas = in.nextDouble();
		switch(tipo) { //CC = 0	CCog = 1
		case SENIOR: //CC = 1	CCog = 1
			vendedor = new VendedorSenior(nombre, idIn, dni);
			break;
		case JUNIOR: //CC = 2	CCog = 1
			vendedor = new VendedorJunior(nombre, idIn, dni);
			break;
		case PRACTICAS: //CC = 3	CCog = 1
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
	//WMC = 5
	//CCog = 6
	private void vuelcaDatos() throws IOException {
		PrintWriter out = null;
		List<Vendedor> listaSenior = new LinkedList<Vendedor>();
		List<Vendedor> listaJunior = new LinkedList<Vendedor>();
		List<Vendedor> listaPracticas = new LinkedList<Vendedor>();

		//añadir vendedores a listas dependiendo el tipo
		for (Vendedor v : listaVendedores) { //CC = 1	CCog = 1
			if (v instanceof VendedorPracticas) { //CC = 2	CCog = 3
				listaPracticas.add(v);
			} else if (v instanceof VendedorJunior) { //CC = 3	CCog = 4		//modificado el if 
				listaJunior.add(v);
			}else { //CC = 3	CCog = 5
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
			if (out != null) //CC = 4 CCog = 6
				out.close();
		}
	}

	/**
	 * Método que va actualizando el fichero
	 * @param salida puntero para escribir sobre el fichero
	 * @param lista lista que se va a escribir
	 */
	//WMC = 2
	//CCog = 1
	private void imprimirInfoLista(PrintWriter salida,List<Vendedor> lista) {

		for (Vendedor v : lista) { //CC = 1	CCog = 1
			salida.println("  Nombre: " + v.getNombre() + " Id: " + v.getId() + " DNI: "+ v.getDni()+" TotalVentasMes: "
					+ v.getTotalVentas());
		}
	}

}
