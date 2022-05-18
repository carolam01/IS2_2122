package es.unican.is2.gestionTiendaRefactorizadoCalidad.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import es.unican.is2.gestionTiendaRefactorizadoCalidad.Tienda;
import es.unican.is2.gestionTiendaRefactorizadoCalidad.Vendedor;
import fundamentos.Menu;
import fundamentos.Lectura;
import fundamentos.Mensaje;

/**
 * Gestión de las comisiones de vendedores de una tienda
 */
//WMC = 12
//WMCn = 12/2 = 6
//CCog = 21
public class GestionComisiones {

	/**
	 * Programa principal basado en menu
	 */
	//WMC = 11
	//CCog = 21
	public static void main(String[] args) {
		// opciones del menu
		final int NUEVA_VENTA = 0;
		final int VENDEDOR_DEL_MES = 1;
		final int VENDEDORES = 2;

		// variables auxiliares
		String dni;
		Lectura lect;

		List<Vendedor> vendedores;
		List<Vendedor> resultado;
		String msj;

		// crea la tienda
		Tienda tienda = new Tienda("C:\\Windows\\Temp\\datosTienda.txt");
		tienda.leeFichero(); //leo el fichero para tener los datos guardados del fichero

		// crea la ventana de menu
		Menu menu = new Menu("Comisiones tienda");
		menu.insertaOpcion("Añadir venta", NUEVA_VENTA);
		menu.insertaOpcion("Vendedor del mes", VENDEDOR_DEL_MES);
		menu.insertaOpcion("Vendedores por ventas", VENDEDORES);
		int opcion;
		// lazo de espera de comandos del usuario
		while (true) { //WMC = 1	CCog = 1
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {//WMC = 1	CCog = 3
			case NUEVA_VENTA: //WMC = 2	CCog = 3
				lect = new Lectura("Datos Venta");
				lect.creaEntrada("Id Vendedor", "");
				lect.creaEntrada("Importe", "");
				lect.esperaYCierra();
				dni = lect.leeString("Id Vendedor");
				double importe = lect.leeDouble("Importe");
				try {
					if (!tienda.anhadeVenta(dni, importe)) { //WMC = 3	CCog = 6
						mensaje("ERROR", "El vendedor no existe");
					}
				} catch (IOException e) { //WMC = 3	CCog = 7
					mensaje("ERROR", "No se pudo guardar el cambio");
				}
				break;

			case VENDEDOR_DEL_MES: //WMC = 4	CCog = 7
				vendedores = tienda.vendedores();
				resultado = new LinkedList<>();
				double maxVentas = 0.0;
				for (Vendedor v : vendedores) { //WMC = 5	CCog = 10
					if (v.getTotalVentas() > maxVentas) { //WMC = 6	CCog = 14
						maxVentas = v.getTotalVentas();
						resultado.clear();
						resultado.add(v);
					} else if (v.getTotalVentas() == maxVentas) { //WMC = 7	CCog = 15
						resultado.add(v);
					}
				}

				msj = "";
				for (Vendedor vn : resultado) { //WMC = 8	CCog = 18
					msj += vn.getNombre() + "\n";
				}
				mensaje("VENDEDORES DEL MES", msj);
				break;


			case VENDEDORES: //WMC = 9	CCog = 18

				vendedores = tienda.vendedores();
				System.out.println(vendedores.size());
				Collections.sort(vendedores, new ComparadorVendedorVentas());			
				msj = "";
				for (Vendedor vn : vendedores) { //WMC = 10	CCog = 21
					msj += vn.getNombre() + " " + vn.getTotalVentas() + "\n";
				}
				mensaje("VENDEDORES", msj);
				break;
				
			default:
				System.exit(-1);
				break;
			}
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo Titulo de la ventana
	 * @param txt Texto contenido en la ventana
	 */
	//WMC = 1
	//CCog = 0
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}
	//WMC = 3
	//WMCn = 3/1 = 3
	//CCog = 2
	public static class ComparadorVendedorVentas implements Comparator<Vendedor>  {
		//WMC = 3
		//CCog = 2
		public int compare(Vendedor o1, Vendedor o2) {
			if (o1.getTotalVentas()>o2.getTotalVentas()) //VMC = 1 	CCog = 1
				return -1;
			else if (o1.getTotalVentas()<o2.getTotalVentas()) //VMC = 2 CCog = 2 
				return 1;
			return 0;
		}

	}


}
