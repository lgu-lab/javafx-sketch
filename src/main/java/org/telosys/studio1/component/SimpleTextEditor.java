package org.telosys.studio1.component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javafx.scene.control.TextArea;

/**
 *
 */
public class SimpleTextEditor {
	
    private final TextArea textArea = new TextArea();
    private final File     file ;

    private boolean  modified = false;

    public SimpleTextEditor(File file) {
		super();
		this.modified = false;
		this.file = file;
		loadFileContent();
	}

	public boolean isModified() {
        return modified;
    }
    
    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public TextArea getTextArea() {
        return textArea;
    }

//    public void setText(String text) {
//        textArea.setText(text);
//    }
//    
//    public String getText() {
//        return textArea.getText();
//    }
    
    private void loadFileContent() {
        //String openFileName = file.getAbsolutePath();
        StringBuffer sb = new StringBuffer();
        try ( FileInputStream fis = new FileInputStream(file);
              BufferedInputStream bis = new BufferedInputStream(fis) ) {
            while ( bis.available() > 0 ) {
                sb.append((char)bis.read());
            }
        }
        catch ( Exception e ) {
            throw new RuntimeException("Cannot load file " + file.getName(), e);
        }
        textArea.setText( sb.toString() );
    }

    public void saveFileContent() {
    	try ( FileOutputStream fos = new FileOutputStream(file);
    			BufferedOutputStream bos = new BufferedOutputStream(fos) ) {
    		
			String text = textArea.getText();
			bos.write(text.getBytes());
			bos.flush();
		}
		catch ( Exception e ) {
            throw new RuntimeException("Cannot save file " + file.getName(), e);
		}
    }
}
