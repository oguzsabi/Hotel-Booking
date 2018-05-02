package BookingPack;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewStage {
    public NewStage(String title, Scene scene, Stage stage){
        stage = new Stage();
        stage.setTitle(title);

        stage.setScene(scene);
        stage.show();
    }
}
