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
	
	public Contribuyente altaContribuyente(Contribuyente c) {
		// TODO
		return null;
	}

	
	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValida {
		Contribuyente contribuyenteElegido = contribuyentes.contribuyente(dni);
		if (contribuyenteElegido.getVehiculos().size() > 0) { 
			throw new OperacionNoValida(dni);
		}
		contribuyentes.eliminaContribuyente(dni);
		return contribuyenteElegido;		
	 }
	
	public Contribuyente contribuyente(String dni) {
		// TODO
		Contribuyente contribuyenteElegido = contribuyentes.contribuyente(dni);
		if (contribuyenteElegido == null) {
			
		}
		return null;
	}

	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValida {
		// TODO
		return null;
	}

	
	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValida {
		// TODO
		return null;
	}

	public Vehiculo vehiculo(String matricula) {
		// TODO
		return null;
	}	
}

