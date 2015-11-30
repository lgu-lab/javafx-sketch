package org.telosys.studio1.component;

import org.telosys.studio1.MainActions;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarBuilder {
	
	private final MainActions _mainActions ;

	public MenuBarBuilder(MainActions _mainActions) {
		super();
		this._mainActions = _mainActions;
	}

	public MenuBar buildMenuBar() {
		/* Create a new MenuBar. */
		MenuBar menuBar = new MenuBar();
		
		/* Create new sub menus. */
		//Menu menuFile = new Menu("File");
		menuBar.getMenus().add(new Menu("File"));
		
		//Menu configurationMenu = new Menu("Configuration");
		menuBar.getMenus().add( buildConfigurationMenu() );
		
//		Menu menuHelp = new Menu("Help");
//		
//		MenuItem about = new MenuItem("About");
//		about.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				/*
//				 * Implement dialog to be prompted when users asks for
//				 * details.
//				 */
//			}
//		});
//		menuHelp.getItems().add(about);
//
//		/* Adding all sub menus at ones to a MenuBar. */
//		menuBar.getMenus().addAll(menuFile, menuHelp);
		
		menuBar.getMenus().add( buildHelpMenu() );
		
		return menuBar ;
	}
	
	private Menu buildConfigurationMenu() {
		Menu menu = new Menu("Configuration");
		menu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				_mainActions.showConfigurationView();
			}
		});
		return menu ;
	}

	private Menu buildHelpMenu() {
		Menu menuHelp = new Menu("Help");
		
		MenuItem about = new MenuItem("About");
		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				/*
				 * Implement dialog to be prompted when users asks for
				 * details.
				 */
			}
		});
		menuHelp.getItems().add(about);
		return menuHelp;
	}
}
