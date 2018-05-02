package BookingPack;

import javafx.scene.input.MouseEvent;
import java.io.IOException;

public interface RemovableScene {
    void removingScene(MouseEvent mouseEvent, String source) throws IOException;
}
