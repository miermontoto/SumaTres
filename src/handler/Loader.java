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

    private final String title;
    private final FileNameExtensionFilter fnef;

    public Loader(String windowTitle, FileNameExtensionFilter fnef) {
        this.title = windowTitle;
        this.fnef = fnef;
    }

    public File load() {
        JFileChooser jfc = jfcGenerator();
        int res = jfc.showOpenDialog(null);
        return res == JFileChooser.APPROVE_OPTION ? jfc.getSelectedFile() : null;
    }

    private JFileChooser jfcGenerator() {
        JFileChooser jfc = new JFileChooser("./");
        jfc.setMultiSelectionEnabled(false);
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setDialogTitle(title);
        jfc.setFileFilter(fnef);
        return jfc;
    }

    public String loadString() {
        File f = this.load();
        if(f == null) return null;
        try {
            return Files.readString(f.toPath());
        } catch (IOException ex) {return null;}
    }

    public File save() {
        JFileChooser jfc = jfcGenerator();
        int res = jfc.showSaveDialog(null);
        return res == JFileChooser.APPROVE_OPTION ? jfc.getSelectedFile() : null;
    }

}
