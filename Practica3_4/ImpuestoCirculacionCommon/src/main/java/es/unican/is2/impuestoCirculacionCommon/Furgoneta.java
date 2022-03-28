package es.unican.is2.impuestoCirculacionCommon;
import java.io.Serializable;
import java.time.LocalDate;
@SuppressWarnings("serial")
public class Furgoneta extends Vehiculo implements Serializable{

	private double potencia;
	private boolean comercial;


	public Furgoneta(String matricula, LocalDate fechaMatriculacion, double potencia, boolean comercial) throws DatoNoValido {
		super(matricula, fechaMatriculacion);
		if (potencia <= 0) {
			throw new DatoNoValido("No creado vehiculo con matricula "+matricula+ " potencia incorrecta " + potencia);
		}
		this.potencia = potencia;
		this.comercial = comercial;
	}

	/**
	 * Retorna el valor del atributo comercial
	 * @return true si la furgoneta es de uso comercial
	 *         false si no es de uso comercial
	 */
	public boolean getComercial() {
		return comercial;
	}

	/**
	 * Retorna la potencia de la furgoneta
	 * @return potencia en caballos fiscales
	 */
	public double getPotencia() {
		return potencia;
	}


	@Override
	public double precioImpuesto() {
		double precio = -1;
		//En primer lugar si el vehículo tiene una antiguedad de mas de 25 años no paga
		if(this.getFechaMatriculacion().isBefore(LocalDate.now().minusYears(25)) ||this.getFechaMatriculacion().isEqual(LocalDate.now().minusYears(25)) ) {
			precio = 0;
			//En caso de que deba pagar en funcion de la potencia pagará una cantidad u otra
		} else {
			if (potencia < 8 && potencia > 0){
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
			if (this.comercial == true) {
				precio = precio * 0.8;
			}
		}
		return precio;
	}
}
