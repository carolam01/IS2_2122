package es.unican.is2.impuestoCirculacionCommon;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class Contribuyente implements Serializable {

    private List<Vehiculo> vehiculos = new LinkedList<Vehiculo>() ;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dni;
    
	public Contribuyente(String nombre, String apellido1, String apellido2, String dni) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
	}
	
    /**
     * Retorna el total a pagar por el impuesto 
     * de circulacion de todos sus vehiculos
     * @return Valor del impuesto a pagar
     */
    public double totalAPagar() {
    	//El contribuyente deberá pagar la suma de los precios de sus vehículos
    	double precioTotal = 0;
    	for (Vehiculo v: vehiculos) {
    		precioTotal += v.precioImpuesto();
    	}
    	
    	return precioTotal;
    }
    
    /**
     * Retorna la lista de vehiculos del contribuyente
     * @return lista de vehiculos del contribuyente
     */
	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	
	/**
	 * Retorna el valor del atributo nombre
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
	
		Contribuyente other = (Contribuyente) obj;
		if (vehiculos == null) {
			if (other.vehiculos != null)
				return false;
		} else if (!vehiculos.equals(other.vehiculos))
			return false;
		return true;
	}

	/**
	 * Retorna el valor del atributo apellido1
	 * @return primer apellido
	 */
	public String getApellido1() {
		return apellido1;
	}
	
	/**
	 * Retorna el valor del atributo apellido2
	 * @return segundo apellido
	 */
	public String getApellido2() {
		return apellido2;
	}
	
	/**
	 * Retorna el valor del dni del contribuyente
	 * @return dni
	 */
	public String getDni() {
		return dni;
	}
    

	
}
