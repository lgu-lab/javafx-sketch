package org.telosys.studio1;

import java.io.File;

import org.telosys.studio1.commons.ViewUtil;
import org.telosys.studio1.view.config.ConfigController;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainActions {

	private TabPane _tabPane ;
	
	
//	public MainActions(TabPane tabPane) {
//		super();
//		this._tabPane = tabPane;
//	}

	public void init(TabPane tabPane) {
		this._tabPane = tabPane;
	}
	
	public void showConfigurationView() {
		System.out.println("showConfigurationView()");
		
		Node view = ViewUtil.loadView("Config.fxml", new ConfigController() );
		
		Tab tab = new Tab();
        tab.setText("Configuration");
        tab.setContent(view);
		_tabPane.getTabs().add(tab);
		
	}

	public void showFileEditorView(File file) {
		System.out.println("showFileEditorView()");
		
		Node view = ViewUtil.loadView("Config.fxml", new ConfigController() );
		
		Tab tab = new Tab();
        tab.setText("File editor");
        tab.setContent(view);
		_tabPane.getTabs().add(tab);
		
	}
}
