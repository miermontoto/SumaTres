package util;

public class Input {
	
	/**
	 * Constrctor generado para cumplir con SonarLint:S1118.
	 * 
	 * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
	 * 		Regla SonarLint:S1118 </a>
	 */
	private Input() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	* Método que le pide al usuario números enteros dentro de un rango.
	* <p>
	* En caso de que se pulse 'cancelar' o se introduzcan caracteres que no puedan
	* ser convertidos a números enteros, el programa devuelve un valor
	* <code> -1 </code> o <code> 999 </code>, dependiendo de si -1 se encuentra en
	* el rango introducido. Cualquier método que utilice esto para obtener enteros,
	* debería estar preparado  en caso de recibir el valor de error. Si el usuario
	* consigue causar una excepción, el programa lo resuelve con un <code>catch</code>
	* que devuelve el valor de error.
	* 
	* @param s     		 Cadena que define lo que se le va a preguntar al usuario.
	* @param lowerLimit  Número entero que define el valor mínimo del rango de posibles
	* 					 respuestas.
	* @param higherLimit Número entero que define el valor máximo del rango de posibles
	* 					 respuestas.
	 */
	public static int input(String s, int lowerLimit, int upperLimit) {
		int value;
		int errorVal = lowerLimit < -1 ? 999 : -1;
		
		try {
			String respuesta = Dialog.input(s);
			if(respuesta == null || respuesta.length() == 0) value = errorVal;
			else value = Integer.parseInt(respuesta);
			
			while ((value < lowerLimit || value > upperLimit) && value != errorVal) {
				Dialog.showError();
				respuesta = Dialog.input(s);
				if(respuesta == null || respuesta.length() == 0) value = errorVal;
				else value = Integer.parseInt(respuesta);
			}
		} catch (Exception ex) {
			Dialog.showExceptionError(ex);
			value = errorVal;
		}
		return value;
	}
}
