/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package handler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase estática que carga archivos.
 * @author Juan Mier
 */
public class Loader {
    
    /**
     * Constrctor generado para cumplir con SonarLint:S1118.
     * 
     * @see <a href="https://sonarcloud.io/organizations/default/rules?languages=java&open=java%3AS1118&q=S1118">
     * 		Regla SonarLint:S1118 </a>
     */
    private Loader() {
        throw new IllegalStateException("File handling class");
    }
    
    public static File load(String title, FileNameExtensionFilter fnef) {
        JFileChooser jfc = new JFileChooser("./");
        jfc.setMultiSelectionEnabled(false);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setDialogTitle(title);
        jfc.setFileFilter(fnef);
        int res = jfc.showOpenDialog(null);
        return res == JFileChooser.APPROVE_OPTION ? jfc.getSelectedFile() : null;
    }
    
    public static String loadString(String title, FileNameExtensionFilter fnef) {
        File f = Loader.load(title, fnef);
        if(f == null) return null;
        try {
            return Files.readString(f.toPath());
        } catch (IOException ex) {return null;}
    }
    
}
