package deprecated;

import util.Dialog;

@Deprecated
public final class Input {
	
	private static int errorVal;
	private static boolean exit;
	private static String ask;
	
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
	 * consigue causar una excepción, el programa vuelve a intentar obtener un valor
	 * válido del usuario.
	 * 
	 * @param s     	  Cadena que define lo que se le va a preguntar al usuario.
	 * @param lowerLimit  Número entero que define el valor mínimo del rango de posibles
	 * 					  respuestas.
	 * @param higherLimit Número entero que define el valor máximo del rango de posibles
	 * 					  respuestas.
	 * @param exitOnError Valor booleano que decide si se sale del programa mediante
	 * 					  <code>System.exit()</code> al encontrarse con algo que determine
	 * 					  como 'error'. Un error es una cadena vacía, o una selección del
     *                    botón 'Cancelar'.
	 */
	public static int input(String s, int lowerLimit, int upperLimit, boolean exitOnError) {
		errorVal = lowerLimit <= -1 && upperLimit >= -1 ? 999 : -1 ;
		ask = s;
		exit = exitOnError;
		int value = 0;
		/*
		 * Se utilizan valores estáticos aunque sea metodológicamente horrible para evitar
		 * tener que pasar muchos parámetros contínuamente a parseResponse(). Funciona y es
		 * limpio.
		 */
		
		try {
			value = parseResponse();
			while ((value < lowerLimit || value > upperLimit) && value != errorVal) {
				Dialog.showError();
				value = parseResponse();
			}
		} catch (Exception ex) {
			Dialog.showError(ex);
			value = input(s, lowerLimit, upperLimit, exitOnError);
		}
		return value;
	}
	
	public static int parseResponse() {
		String x = Dialog.input(ask);
		int value = errorVal;
		if (x == null || x.length() == 0) {
			if(exit) System.exit(0);
		} else value = Integer.parseInt(x);
		
		return value;
	}
}
