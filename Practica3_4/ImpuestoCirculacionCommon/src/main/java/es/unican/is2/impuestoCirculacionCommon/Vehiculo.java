
package es.unican.is2.impuestoCirculacionCommon;

import java.io.Serializable;
import java.time.LocalDate;


@SuppressWarnings("serial")
public abstract class Vehiculo implements Serializable{

	private String matricula;
	private LocalDate fechaMatriculacion;	


	public Vehiculo(String matricula, LocalDate fechaMatriculacion)throws NullPointerException {
		if(matricula == null || fechaMatriculacion == null) {
			throw new NullPointerException();
		}else {
			this.matricula = matricula;
			this.fechaMatriculacion = fechaMatriculacion;
		}
	}


	/**
	 * Retorna el valor del impuesto de circulacion
	 *  @return valor del impuesto circulacion
	 */
	public abstract double precioImpuesto (); 


	/**
	 * Retorna la matricula del vehiculo
	 * @return matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Retorna la fecha de matriculacion del vehiculo
	 * @return fecha de matriculacion
	 */
	public LocalDate getFechaMatriculacion() {
		return fechaMatriculacion;
	}


	@Override
	public int hashCode() {
		return matricula.hashCode(); 
	}

	@Override
	public boolean equals(Object obj) {
		return matricula.equals(((Vehiculo) obj).matricula);
	}


}
