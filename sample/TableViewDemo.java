package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;

public class TableViewDemo extends Application {
    TableView<Product> table;
    Stage window;
    TextField nameInput, priceInput, quantityInput;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage primaryStage){
        window = primaryStage;
        window.setTitle("Table View Demo");

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price(TL)");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        nameInput = new TextField();
        nameInput.setPromptText("Name");

        priceInput = new TextField();
        priceInput.setPromptText("Price");

        quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");

        Button addButton = new Button("Add");
        addButton.setOnAction(event -> addButtonClicked());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> deleteButtonClicked());

        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.getChildren().addAll(nameInput,priceInput,quantityInput,addButton,deleteButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table,hBox);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    public void addButtonClicked(){
        Product product = new Product();
        product.setName(nameInput.getText());
        product.setPrice(Double.parseDouble(priceInput.getText()));
        product.setQuantity(Integer.parseInt(quantityInput.getText()));
        table.getItems().add(product);
        nameInput.clear();
        priceInput.clear();
        quantityInput.clear();
    }

    public void deleteButtonClicked(){
        ObservableList<Product> selectedProduct, allProducts;
        allProducts = table.getItems();
        selectedProduct = table.getSelectionModel().getSelectedItems();

        selectedProduct.forEach(allProducts::remove);
    }

    public ObservableList<Product> getProduct(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("GTX 1080",2499.99,15));
        products.add(new Product("GTX 1070",2099.99,18));
        products.add(new Product("GTX 1060",1799.99,40));
        products.add(new Product("GTX 1050",1599.99,53));
        products.add(new Product("GTX 1050 Ti",1699.99,21));
        return products;
    }
}
