package org.telosys.studio1;

import java.io.File;

import org.telosys.studio1.commons.ViewUtil;
import org.telosys.studio1.component.SimpleTextEditor;
import org.telosys.studio1.view.config.ConfigController;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainActions {

	private Stage   _stage;
	private TabPane _tabPane ;
	
	protected void init(Stage stage, TabPane tabPane) {
		this._stage = stage ;
		this._tabPane = tabPane;
	}
	
	public void exit() {
		System.out.println("exit()");
		_stage.close();
	}
	
	public void showConfigurationView() {
		System.out.println("showConfigurationView()");
		
		Node view = ViewUtil.loadView("Config.fxml", new ConfigController() );
		
		Tab tab = new Tab();
        tab.setText("Configuration");
        tab.setContent(view);
		_tabPane.getTabs().add(tab);
		
	}

	public void showTextEditorView(File file) {
		System.out.println("showTextEditorView()");
		
	    // Create the editor with this content and store it
	    SimpleTextEditor editor = new SimpleTextEditor(file);
	    //editor.setText( file.getName() );
	    //editor.filename = openFileName;

	    // Create a tab to house the new editor
	    Tab tab = new Tab();
	    tab.setUserData(editor);
	    tab.setText(file.getName());
	    tab.setContent(editor.getTextArea());
	    _tabPane.getTabs().add(tab);
	    _tabPane.getSelectionModel().select(tab); // set this tab as the "active" tab
	}

	public void closeCurrentFile() {
		System.out.println("closeCurrentFile()");
		
        if ( _tabPane.getTabs().size() > 0 ) {
        	Tab selectedTab = _tabPane.getSelectionModel().getSelectedItem();        	
	        if ( _tabPane.getTabs().contains(selectedTab) ) {
	        	// TODO 
	        	// check is file modified and need "save"
	            _tabPane.getTabs().remove(selectedTab);
	        }
	        else {
	        	throw new RuntimeException("Current tab not found");
	        }
        }
	}

	public void closeAll() {
		System.out.println("closeAll()");
		
		ObservableList<Tab> tabs = _tabPane.getTabs() ;
        if ( tabs.size() > 0 ) {
	        for ( Tab tab : tabs ) {
	        	// TODO 
	        	// check is file modified and need "save"
	            
	        }
            tabs.clear();
        }
	}

	public void saveCurrentFile() {
		System.out.println("saveCurrentFile()");
		
        if ( _tabPane.getTabs().size() > 0 ) {
	        Tab selectedTab = _tabPane.getSelectionModel().getSelectedItem();
	        Object tabData = selectedTab.getUserData();
	        if ( tabData instanceof SimpleTextEditor ) {
	        	SimpleTextEditor editor = (SimpleTextEditor) tabData ;
	        	editor.saveFileContent();
	        }
	        else {
	        	throw new RuntimeException("Unknown editor");
	        }
        }
	}

}
