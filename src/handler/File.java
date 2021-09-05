package handler;

import static java.lang.System.out;
import java.io.FileWriter;

import util.Dialog;

public class File {
	
	/**
	 * Constrctor generado para cumplir con SonarLint:S1118.
	 * 
	 * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
	 * 		Regla SonarLint:S1118 </a>
	 */
	private File() {
		throw new IllegalStateException("File handling class");
	}
	
	public static void write(String s, java.io.File file) {
		try {
			if(file.createNewFile()) {out.println("Creado archivo.");}
			final FileWriter writer = new FileWriter(file, true);
			writer.write(s);
			writer.close();
		} catch (Exception e) {
			Dialog.showError(e);
		}
	}
}
