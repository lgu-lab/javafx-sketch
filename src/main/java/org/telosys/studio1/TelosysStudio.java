package org.telosys.studio1;

import org.telosys.studio1.commons.ViewUtil;
import org.telosys.studio1.component.MenuBarBuilder;
import org.telosys.studio1.component.WorkspaceBuilder;
import org.telosys.studio1.view.config.ConfigController;
import org.telosys.studio1.view.files.ProjectFile;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class TelosysStudio extends Application {
	
	private final static String WORKSPACE_PATH = "D:/TMP/FakeWorkspace";
	
	private TabPane _tabPane = null ;
	private MainActions _mainActions = null ;
	
	private ToolBar buildToolBar() {
		/* Create a button. */
		Button btn = new Button();
		btn.setText("Add tab");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Add tab");
				
				Node view = ViewUtil.loadView("Config.fxml", new ConfigController() );
				
				Tab tab = new Tab();
	            tab.setText("Configuration");
	            tab.setContent(view);
				_tabPane.getTabs().add(tab);
			}
		});

		/* Create a new ToolBar. */
		ToolBar tools = new ToolBar(btn);
		return tools ;
	}
	
	private VBox buildPartTop() {
		MenuBarBuilder menuBarBuilder = new MenuBarBuilder(_mainActions);
		//MenuBar menuBar = buildMenuBar();
		MenuBar menuBar = menuBarBuilder.buildMenuBar();
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
//		TreeView<File> fileTreeView = new TreeView<File>(
//				new SimpleFileTreeItem(new File(WORKSPACE_PATH)));

		WorkspaceBuilder tvb = new WorkspaceBuilder(_mainActions);
		TreeView<ProjectFile> fileTreeView = tvb.buildTreeView(WORKSPACE_PATH);
		_tabPane = new TabPane();

		/* Creating a SplitPane and adding the fileView. */
		SplitPane splitPane = new SplitPane();
		splitPane.getItems().add(fileTreeView);
		splitPane.getItems().add(_tabPane);
		
		return splitPane ;
	}
	
	@Override
	public void start(Stage primaryStage) {
 
		_mainActions = new MainActions() ;
		
		VBox menus = buildPartTop();
 
		SplitPane splitPane = buildPartMiddle();
			
		/* Create a root node as BorderPane. */
		BorderPane root = new BorderPane();
 
		/* Adding the menus as well as the content pane to the root node. */
		root.setTop(menus);
		root.setCenter(splitPane);
 
		_mainActions.init(primaryStage, _tabPane);
		try {
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
			primaryStage.setTitle("Telosys Studio - " + WORKSPACE_PATH );
 
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