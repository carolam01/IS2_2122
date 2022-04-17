package es.unican.is2.impuestoCirculacionMain;

import static org.junit.Assert.assertEquals;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.unican.is2.impuestoCirculacionBusiness.GestionImpuestoCirculacion;
import es.unican.is2.impuestoCirculacionDAO.*;
import es.unican.is2.impuestoCirculacionGUI.VistaFuncionario;


public class RunnerTest {
	private FrameFixture demo;

	@Before
	public void setUp() {
		// Componentes capa DAO
		ContribuyentesDAO contribuyentesDAO = new ContribuyentesDAO();
		VehiculosDAO vehiculosDAO = new VehiculosDAO();

		// Componentes capa negocio
		GestionImpuestoCirculacion negocio = new GestionImpuestoCirculacion(contribuyentesDAO, vehiculosDAO);

		// Componentes casa presentacion
		VistaFuncionario gui = new VistaFuncionario(negocio, negocio, negocio);

		demo= new FrameFixture(gui);
		gui.setVisible(true);
	}

	@After
	public void tearDown() {
		demo.cleanUp();
	}

	@Test
	public void testConsultaContribuyenteValido() {
		//comprobamos consulta contribuyente

		////////////////////////////////////caso valido///////////////////////////////////////////

		//Escribimos en el campo de texto
		demo.textBox("txtDniContribuyente").enterText("11111111A");

		//pulsamos el boton
		demo.button("btnBuscar").click();

		//Comprobamos la salida

		//nombre
		demo.textBox("txtNombreContribuyente").requireText("Pepe López Martínez");
		demo.list("listaMatriculas").requireItemCount(2); //salia mal ya que hay que añadir todos los vehiculos a la lista (Error1)

		//vehiculos
		String sut1 = demo.list("listaMatriculas").valueAt(0);
		assertEquals(sut1,"1111-AAA");
		String sut2 = demo.list("listaMatriculas").valueAt(1);
		assertEquals(sut2,"1111-BBB");

		//total a pagar
		demo.textBox("txtTotalContribuyente").requireText("403.2");


		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testConsultaContribuyenteNoValido1() {
		/////////////////////////////caso no valido 1(campo dni vacio)/////////////////////////////////

		//Escribimos en el campo de texto
		demo.textBox("txtDniContribuyente").enterText("");

		//pulsamos el boton
		demo.button("btnBuscar").click();

		//Comprobamos la salida

		//nombre
		demo.textBox("txtNombreContribuyente").requireText("DNI No Válido");
		demo.list("listaMatriculas").requireItemCount(0); //no tiene que salir nigun vehiculo
		

		//total a pagar
		demo.textBox("txtTotalContribuyente").requireText("0");


		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConsultaContribuyenteNoValido2() {
		/////////////////////////////caso no valido 2(dni que no existe)/////////////////////////////////

		//Escribimos en el campo de texto
		demo.textBox("txtDniContribuyente").enterText("1ABC");

		//pulsamos el boton
		demo.button("btnBuscar").click();

		//Comprobamos la salida

		//nombre
		demo.textBox("txtNombreContribuyente").requireText("DNI No Válido");
		demo.list("listaMatriculas").requireItemCount(0); //no tiene que salir nigun vehiculo
		

		//total a pagar
		demo.textBox("txtTotalContribuyente").requireText("0");


		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}