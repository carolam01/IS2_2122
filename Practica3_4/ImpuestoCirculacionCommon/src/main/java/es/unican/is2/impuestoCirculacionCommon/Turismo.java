package es.unican.is2.impuestoCirculacionCommon;

import java.io.Serializable;
import java.time.LocalDate;


@SuppressWarnings("serial")
public class Turismo
    extends Vehiculo implements Serializable
{
	private double potencia;
	
	
	public Turismo(String matricula, LocalDate fechaMatriculacion, double potencia) throws DatoNoValido {
		super(matricula, fechaMatriculacion);
		if(potencia <= 0) {
    		throw new DatoNoValido("No creado vehiculo con matricula "+matricula+ " potencia incorrecta " + potencia);
    	}else {
    		this.potencia = potencia;
    	}
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
		double precio = -1;
		//En primer lugar si el vehículo tiene una antiguedad de mas de 25 años no paga
				if(this.getFechaMatriculacion().isBefore(LocalDate.now().minusYears(25)) ||this.getFechaMatriculacion().isEqual(LocalDate.now().minusYears(25)) ) {
					precio = 0;
			//En caso de que deba pagar en funcion de la potencia pagará una cantidad u otra
		} else {
			if (potencia < 8){
				precio = 25.24;
			}else if(potencia < 12) {
				precio = 68.16;
			} else if(potencia < 16) {
				precio = 143.88;
			} else if(potencia < 20) {
				precio = 179.22;
			} else {
				precio = 224;
			}
		}
    	return precio;
    }
    
}
