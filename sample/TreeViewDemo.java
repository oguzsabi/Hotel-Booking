package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TreeViewDemo extends Application{
    Stage window;
    Scene scene;
    TreeView<String> tree;
    TreeItem<String> root, edit, file;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage){
        window = primaryStage;
        window.setTitle("Tree View Demo");

        root = new TreeItem<>("Root");
        root.setExpanded(true);

        file = createBranch("File",root);
        createBranch("New",file);
        createBranch("Open",file);
        createBranch("Settings",file);
        createBranch("Save",file);
        createBranch("Exit",file);

        edit = createBranch("Edit",root);
        createBranch("Find",edit);
        createBranch("Extend Selection",edit);
        createBranch("Select All",edit);
        createBranch("Toggle Case",edit);
        createBranch("Convert Indents",edit);

        tree = new TreeView<>(root);
        tree.setShowRoot(true);

        StackPane layout = new StackPane();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(tree);
        scene = new Scene(layout,300,320);

        window.setScene(scene);
        window.show();
    }

    public TreeItem<String> createBranch(String title, TreeItem<String> parent){
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}
