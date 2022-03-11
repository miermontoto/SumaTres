package handler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import util.Dialog;

/**
 * Clase de utilidad que permite escribir cadenas de texto a archivos de manera
 * rápida y fiable. Debido a que es una clase de utilidad, no se puede instanciar.
 * @author Juan Mier.
 */
public class FileWS {
	
    /**
     * Constrctor generado para cumplir con SonarLint:S1118.
     * 
     * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
     * 		Regla SonarLint:S1118 </a>
     */
    private FileWS() {
        throw new IllegalStateException("File handling class");
    }

    /**
     * Método que escribe una cadena a un archivo.
     * Si el archivo no existe, se crea.
     * @param s Cadena a introducir en el fichero.
     * @param file Archivo en el que escribir.
     */
    public static void write(String s, File file) {
            try {
                if(file.createNewFile()) {System.out.println("Creado archivo.");}
                try (FileWriter writer = new FileWriter(file, true)) {
                    writer.write(s);
                    writer.close();
                }
            } catch (IOException e) {Dialog.showError(e);}
    }
}
