package es.unican.is2.gestionTienda;

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
//WMC = 37
//WMCn = 37/9 = 4.1
//CCog total = 30
public class Tienda {

	private LinkedList<Vendedor> lista = new LinkedList<Vendedor>();
	private String direccion;
	private String nombre;

	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * 
	 * @param datos Path absoluto del fichero de datos
	 */
	//CC = 1
	//CCog = 0
	public Tienda(String datos) {
		this.datos = datos;
	}

	/**
	 * Retorna la dirección de la tienda
	 * @return Dirección de la tienda
	 */
	//CC = 1
	//CCog = 0
	public String direccion() {
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	//CC = 1
	//CCog = 0
	public String nombre() {
		return nombre;
	}

	/**
	 * Añade un nuevo vendedor a la tienda
	 * 
	 * @param nuevoVendedor
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya había un vendedor con el mismo id
	 */
	//CC = 2
	//CCog = 1
	public boolean anhade(Vendedor nuevoVendedor) throws IOException {
		Vendedor v = buscaVendedor(nuevoVendedor.getId());
		if (v != null) { //CC = 1	CCog = 1
			return false;
		}
		lista.add(nuevoVendedor);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo dni se pasa como parámetro
	 * 
	 * @param id
	 * @return true si se elimina el vendedor 
	 *         false si no existe ningún vendedor con el id indicado
	 */
	//CC = 2
	//CCog = 1
	public boolean eliminaVendedor(String id) throws IOException {
		Vendedor v = buscaVendedor(id);
		if (v == null) {	//CC = 1	CCog = 1
			return false;
		}
		lista.remove(v);
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
	//CC = 5
	//CCog = 4
	public boolean anhadeVenta(String id, double importe) throws IOException {
		Vendedor v = buscaVendedor(id);
		if (v == null) {	//CC = 1	CCog = 1
			return false;
		}
		double importeFinal = importe;
		if (v instanceof VendedorEnPlantilla) {	//CC = 2	CCog = 2
			switch (((VendedorEnPlantilla) v).tipo()) {
			case JUNIOR: //CC = 3	CCog = 4
				importeFinal += importeFinal * 0.005;
				break;
			case SENIOR: //CC = 4	CCog = 4
				importeFinal += importeFinal * 0.01;
				break;
			}
		}
		v.anhade(importeFinal);
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese id o null si no existe ninguno
	 */
	//CC = 9
	//CCog = 8
	public Vendedor buscaVendedor(String id) {

		lista = new LinkedList<Vendedor>();
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

				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni= in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.SENIOR);
				ven.setT(totalVentas);
				lista.add(ven);
			}
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals("Prácticas")) { //CC = 4	CCog = 2
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni= in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.JUNIOR);
				ven.setT(totalVentas);
				lista.add(ven);
			}
			while (in.hasNext()) {	//CC = 5	CCog = 3
				in.next();
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni= in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new vendedorEnPracticas(nombre, idIn, dni);
				ven.setT(totalVentas);
				lista.add(ven);
			}
		} catch (FileNotFoundException e) {	//CC = 5	CCog = 4
		} finally {
			if (in != null) { //CC = 6	CCog = 5
				in.close();
			}
		} // try

		for (Vendedor v : lista) {	//CC = 7	CCog = 6
			if (v.getId().equals(id)) {	//CC = 8	CCog = 8
				return v;
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda 
	 * @return La lista de vendedores
	 */
	//CC=7
	//CCog = 5
	public List<Vendedor> vendedores() {
		lista = new LinkedList<Vendedor>();

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
			while (in.hasNext() && !in.next().equals("Junior")) {//CC = 2	CCog = 1

				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni= in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.SENIOR);
				ven.setT(totalVentas);
				lista.add(ven);
			}
			// lee los vendedores junior
			while (in.hasNext() && !in.next().equals("Prácticas")) {//CC = 4	CCog = 2
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni= in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new VendedorEnPlantilla(nombre, idIn, dni, TipoVendedor.JUNIOR);
				ven.setT(totalVentas);
				lista.add(ven);
			}
			while (in.hasNext()) {//CC = 5	CCog = 3
				in.next();
				String nombre = in.next();
				in.next();
				String idIn = in.next();
				in.next();
				String dni= in.next();
				in.next();
				double totalVentas = in.nextDouble();
				ven = new vendedorEnPracticas(nombre, idIn, dni);
				ven.setT(totalVentas);
				lista.add(ven);
			}
		} catch (FileNotFoundException e) {//CC = 5	CCog = 4

		} finally {
			if (in != null) {//CC = 6	CCog = 5
				in.close();
			}
		} // try

		return lista;

	}

	/**
	 * Método que actualiza el fichero datosTienda.txt 
	 * con los datos actualizados de los vendedores
	 */
	//CC=9
	//CCog = 11
	private void vuelcaDatos() throws IOException {
		PrintWriter out = null;
		List<Vendedor> senior = new LinkedList<Vendedor>();
		List<Vendedor> junior = new LinkedList<Vendedor>();
		List<Vendedor> practicas = new LinkedList<Vendedor>();

		for (Vendedor v : lista) {//CC = 1 CCog = 1
			if (v instanceof vendedorEnPracticas) {//CC = 2 CCog = 3
				practicas.add(v);
			} else if (v instanceof VendedorEnPlantilla) { //CC = 3 CCog = 4
				VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
				if (vp.tipo().equals(TipoVendedor.JUNIOR)) //CC = 4	CCog = 7 
					junior.add(vp);
				else
					senior.add(vp);
			}
		}

		try {

			out = new PrintWriter(new FileWriter(datos));

			out.println(nombre);
			out.println(direccion);
			out.println();
			out.println("Senior");
			for (Vendedor v : senior) { //CC = 5 CCog = 8
				VendedorEnPlantilla v1 = (VendedorEnPlantilla) v;
				out.println("  Nombre: " + v1.getNombre() + " Id: " + v1.getId() + " DNI: "+ v1.getDni()+" TotalVentasMes: "
						+ v1.getTotalVentas());
			}
			out.println();
			out.println("Junior");
			for (Vendedor v : junior) { //CC = 6 CCog = 9
				VendedorEnPlantilla v2 = (VendedorEnPlantilla) v;
				out.println("  Nombre: " + v2.getNombre() + " Id: " + v2.getId() + " DNI: "+ v2.getDni()+" TotalVentasMes: "
						+ v2.getTotalVentas());
			}
			out.println();
			out.println("Prácticas");
			for (Vendedor v : practicas) { //CC = 7 CCog = 10
				vendedorEnPracticas v3 = (vendedorEnPracticas) v;
				out.println("  Nombre: " + v3.getNombre() + " Id: " + v3.getId() + " DNI: "+ v3.getDni()+" TotalVentasMes: "
						+ v3.getTotalVentas());
			}

		} finally {
			if (out != null) //CC = 8  CCog = 11
				out.close();
		}
	}

}
