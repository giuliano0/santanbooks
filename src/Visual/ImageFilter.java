package Visual;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;

public class ImageFilter extends javax.swing.filechooser.FileFilter {
    String[] extensions;
    String description;

    public ImageFilter(String descr, String... exts) {
        extensions = new String[exts.length];
        for (int i = exts.length - 1; i >= 0; i--)
            extensions[i] = exts[i].toLowerCase();

        description = descr;
    }

    @Override
    public boolean accept(File f) {
    	boolean accept = false;

    	if (f.isDirectory())
              accept = true;
    	
    	for (String extension : extensions)
            if (f.getName().toLowerCase().endsWith(extension))
                return true;

        return accept;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
