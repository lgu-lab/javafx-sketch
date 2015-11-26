package org.telosys.studio0;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
 
public class TelosysStudio extends Application {
	
	private MenuBar buildMenuBar() {
		/* Create a new MenuBar. */
		MenuBar menuBar = new MenuBar();
		/* Create new sub menus. */
		Menu menuFile = new Menu("File");
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

		/* Adding all sub menus at ones to a MenuBar. */
		menuBar.getMenus().addAll(menuFile, menuHelp);
		
		return menuBar ;
	}
	
	private ToolBar buildToolBar() {
		/* Create a button. */
		Button btn = new Button();
		btn.setText("Say Hello World.");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});

		/* Create a new ToolBar. */
		ToolBar tools = new ToolBar(btn);
		return tools ;
	}
	
	private VBox buildPartTop() {
		MenuBar menuBar = buildMenuBar();
		ToolBar tools = buildToolBar();
		/* Create a new VBox and add the menu as well as the tools. */
		VBox menus = new VBox();
		menus.getChildren().addAll(menuBar, tools);
		return menus ;
	}
	
	private SplitPane buildPartMiddle() {
		/*
		 * Adding a TreeView to the very left of the application window.
		 */
		TreeView<File> fileTreeView = new TreeView<File>(
				new SimpleFileTreeItem(new File("C:\\")));

		/* Creating a SplitPane and adding the fileView. */
		SplitPane splitPane = new SplitPane();
		splitPane.getItems().add(fileTreeView);
		splitPane.getItems().add(new HTMLEditor());
		
		return splitPane ;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
////			/* Create a new MenuBar. */
////			MenuBar menuBar = new MenuBar();
////			/* Create new sub menus. */
////			Menu menuFile = new Menu("File");
////			Menu menuHelp = new Menu("Help");
////			MenuItem about = new MenuItem("About");
////			about.setOnAction(new EventHandler<ActionEvent>() {
////				@Override
////				public void handle(ActionEvent event) {
////					/*
////					 * Implement dialog to be prompted when users asks for
////					 * details.
////					 */
////				}
////			});
////			menuHelp.getItems().add(about);
//// 
////			/* Adding all sub menus at ones to a MenuBar. */
////			menuBar.getMenus().addAll(menuFile, menuHelp);
//			MenuBar menuBar = buildMenuBar();
//			
////			/* Create a button. */
////			Button btn = new Button();
////			btn.setText("Say Hello World.");
////			btn.setOnAction(new EventHandler<ActionEvent>() {
////				@Override
////				public void handle(ActionEvent event) {
////					System.out.println("Hello World!");
////				}
////			});
//// 
////			/* Create a new ToolBar. */
////			ToolBar tools = new ToolBar(btn);
//			ToolBar tools = buildToolBar();
// 
//			/* Create a new VBox and add the menu as well as the tools. */
//			VBox menus = new VBox();
//			menus.getChildren().addAll(menuBar, tools);
 
			VBox menus = buildPartTop();
			
//			/*
//			 * Adding a TreeView to the very left of the application window.
//			 */
//			TreeView<File> fileTreeView = new TreeView<File>(
//					new SimpleFileTreeItem(new File("C:\\")));
// 
//			/* Creating a SplitPane and adding the fileView. */
//			SplitPane splitPane = new SplitPane();
//			splitPane.getItems().add(fileTreeView);
//			splitPane.getItems().add(new HTMLEditor());
 
			SplitPane splitPane = buildPartMiddle();
			
			/* Create a root node as BorderPane. */
			BorderPane root = new BorderPane();
 
			/* Adding the menus as well as the content pane to the root node. */
			root.setTop(menus);
			root.setCenter(splitPane);
 
			/*
			 * Setting the root node of a scene as well as the applications CSS
			 * file. CSS file has to be in same src directory as this class. The
			 * path is always relative.
			 */
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
 
			/* Adding a scene to the stage. */
			primaryStage.setScene(scene);
			primaryStage.setTitle("Telosys Studio");
 
			/* Lift the curtain :0). */
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	public static void main(String[] args) {
		launch(args);
	}
}