package es.unican.is2.impuestoCirculacionBusiness;

import es.unican.is2.impuestoCirculacionCommon.*;


/**
 * Clase que implementa la capa de negocio de la aplicacion
 */
public class GestionImpuestoCirculacion implements IGestionContribuyentes, IGestionVehiculos, IInfoImpuestoCirculacion {

	private IContribuyentesDAO contribuyentes;
	private IVehiculosDAO vehiculos;

	public GestionImpuestoCirculacion(IContribuyentesDAO contribuyentes, IVehiculosDAO vehiculos) {
		this.contribuyentes = contribuyentes;
		this.vehiculos = vehiculos;
	}

	public Contribuyente altaContribuyente(Contribuyente c){

		Contribuyente contribuyente = contribuyentes.contribuyente(c.getDni());

		if (contribuyente != null) { //no se anhade, ya existe
			return null;
		}else {
			contribuyentes.creaContribuyente(c);
			return c;
		}
	}


	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValida {
		
		Contribuyente contribuyente = contribuyentes.contribuyente(dni);

		if (contribuyente == null) { //no se elimina si no existe
			return null;
		}else if(!contribuyente.getVehiculos().isEmpty()){ //si tiene vehiculos a su nombre (lista no vacia)
			throw new OperacionNoValida(dni);
		}else {
			contribuyentes.eliminaContribuyente(dni);
			return contribuyente;
		}
	}

	public Contribuyente contribuyente(String dni) {
		
		Contribuyente contribuyente = contribuyentes.contribuyente(dni);
		
		if (contribuyente == null) { //si el contribuyente no existe se notifica
			return null;
		}else {
			return contribuyente;
		}
	}

	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValida {

		String matriculaVehiculoNuevo = v.getMatricula();
		Contribuyente contribuyente = contribuyentes.contribuyente(dni);
		Vehiculo vehiculo = vehiculos.vehiculo(matriculaVehiculoNuevo);

		if (contribuyente == null) { //si no se encuentra el contribuyente
			return null;
		}else if
		(vehiculo != null) { //si el vehiculo ya existe
			throw new OperacionNoValida(matriculaVehiculoNuevo);
		}else {
			vehiculos.creaVehiculo(v); //anhado el vehiculo al ayuntamiento
			contribuyente.getVehiculos().add(v);//anhado el vehiculo a su contribuyente	
			return v; //retorno vehiculo anhadido
		}
	}


	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValida {

		Contribuyente contribuyente = contribuyentes.contribuyente(dni);
		Vehiculo vehiculoEliminar = vehiculos.vehiculo(matricula);

		if (contribuyente == null) { //si no se encuentra el contribuyente
			return null;
		}else if(vehiculoEliminar == null) { //si el vehiculo no existe
			return null;
		}else if(!contribuyente.getVehiculos().contains(vehiculoEliminar)){ //si el vehiculo no pertenece al dni indicado	
			throw new OperacionNoValida(matricula);
		}else {
			vehiculos.eliminaVehiculo(matricula); //elimino el vehiculo del ayuntamiento
			contribuyente.getVehiculos().remove(vehiculoEliminar); //elimino el vehiculo de la lista del contribuyente
			return vehiculoEliminar; //retorno vehiculo eliminado
		}
	}

	public Vehiculo vehiculo(String matricula) {

		Vehiculo vehiculoConsulta = vehiculos.vehiculo(matricula);

		if (vehiculoConsulta == null) { //si el vehiculo no existe se notifica
			return null;
		}else {
			return vehiculoConsulta;
		}
	}	
}

