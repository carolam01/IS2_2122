package es.unican.is2.impuestoCirculacionCommon;

import java.time.LocalDate;
@SuppressWarnings("serial")
public class Motocicleta extends Vehiculo
{
	private int cilindrada;

	
    public Motocicleta(String matricula, LocalDate fechaMatriculacion, int cilindrada) throws DatoNoValido{
		super(matricula, fechaMatriculacion);

    	if(cilindrada <= 0) {
    		throw new DatoNoValido("No creado vehiculo con matricula "+matricula+ " cilindrada incorrecta " +cilindrada);
    	}else {
    		this.cilindrada = cilindrada;
    	}
		
	}


	/**
     * Retorna la cilindrada de la motocicleta
     * @return cilindrada
     */
    public int getCilindrada() {
        return cilindrada;
    }
    
  
	@Override
    public double precioImpuesto() {
		double precio = -1;
		//En primer lugar si el vehículo tiene una antiguedad de mas de 25 años no paga
		if(this.getFechaMatriculacion().isBefore(LocalDate.now().minusYears(25)) ||this.getFechaMatriculacion().isEqual(LocalDate.now().minusYears(25)) ) {
			precio = 0;
			//En caso de que deba pagar en funcion de la cilindrada pagará una cantidad u otra
		} else {
			if (cilindrada < 125){
				precio = 8.84;
			}else if(cilindrada < 250) {
				precio = 15.14;
			} else if(cilindrada < 500) {
				precio = 30.30;
			} else if(cilindrada < 1000) {
				precio = 60.58;
			} else {
				precio = 121.16;
			}
		}
		return precio;
    }
}
