package es.unican.is2.impuestoCirculacionCommon;

import java.time.LocalDate;

@SuppressWarnings("serial")
public class Motocicleta extends Vehiculo
{
	private int cilindrada;

	
    public Motocicleta(String matricula, LocalDate fechaMatriculacion, int cilindrada) {
		super(matricula, fechaMatriculacion);
		this.cilindrada = cilindrada;
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
		//TODO
		return 0;
    }
}
