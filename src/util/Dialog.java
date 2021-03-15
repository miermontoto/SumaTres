package util;

import javax.swing.JOptionPane;

public class Dialog {
	
	public static final String title = "SumaTres";

	/**
	 * Constrctor generado para cumplir con SonarLint:S1118.
	 * 
	 * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
	 * 		Regla SonarLint:S1118 </a>
	 */
	private Dialog() {
		throw new IllegalStateException("Utility class");
	}
	
	public static boolean confirm(String s) {
		return JOptionPane.showConfirmDialog(null, s, title, JOptionPane.YES_NO_OPTION) == 0;
	}
	
	public static void show(String s) {
		JOptionPane.showMessageDialog(null, s, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void showError(String s) {
		JOptionPane.showMessageDialog(null, s, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public static String input(String s) {
		return JOptionPane.showInputDialog(null, s, title, JOptionPane.QUESTION_MESSAGE);
	}
	
	public static int choices(String s, String[] c) {
		return JOptionPane.showOptionDialog(null, s, title, JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, c, c[0]);
	}
	
	public static void showExceptionError(Exception e) {
		showError("Valor catastrófico: " + e.toString());
	}
	
	public static void showError() {
		showError("Valor inválido");
	}
}
