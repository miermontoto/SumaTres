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
     * Método que machaca una cadena de texto en un archivo.
     * @param string Cadena de texto a escribir.
     * @param file Archivo en el que escribir.
     */
    public static void write(String string, File file) {
        use(string, file, false);
    }

    /**
     * Método que añade una cadena de texto en un archivo.
     * @param string Cadena de texto a escribir.
     * @param file Archivo en el que escribir.
     */
    public static void append(String string, File file) {
        use(string, file, true);
    }

    /**
     * Método que escribe una cadena a un archivo.
     * Si el archivo no existe, se crea.
     * @param s Cadena a introducir en el fichero.
     * @param file Archivo en el que escribir.
     * @param append Establece el modo "append", similar al funcionamiento de Python con <code>open("file", "a")</code>.
     */
    private static void use(String string, File file, boolean append) {
        try {
            if(file.createNewFile()) System.out.println("Creado archivo.");
            try (FileWriter writer = new FileWriter(file, append)) {
                writer.write(string);
                writer.close();
            }
        } catch (IOException e) {Dialog.showError(e);}
    }

}
