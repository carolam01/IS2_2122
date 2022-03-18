package es.unican.is2.impuestoCirculacionCommon;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Turismo
    extends Vehiculo implements Serializable
{

	private double potencia;
	
	
	public Turismo(String matricula, LocalDate fechaMatriculacion, double potencia) {
		super(matricula, fechaMatriculacion);
		this.potencia = potencia;
	}


	/**
	 * Retorna la potencia del turismo
	 * @return potencia en caballos fiscales
	 */
    public double getPotencia() {
        return potencia;
    }
       
    
    /**
     * Retorna el precio del impuesto a pagar
     *  @return precio
     */
	@Override
    public double precioImpuesto() {
		// TODO
    	return 0;
    }
    
}
