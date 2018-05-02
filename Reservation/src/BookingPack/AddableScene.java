package BookingPack;

import javafx.scene.input.MouseEvent;
import java.io.IOException;

public interface AddableScene {
    void addingScene(MouseEvent mouseEvent, String source) throws IOException;
}
