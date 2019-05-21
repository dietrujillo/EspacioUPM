package espacioUPM.UI;

import espacioUPM.Database.DB_Main;
import espacioUPM.Database.IDB_Usuario;
import espacioUPM.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SearchedUser extends VBox {
    private SearchedUserController controller;
    private Node view;

    public SearchedUser() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/SearchedUser.fxml"));
        fxmlLoader.setControllerFactory(param -> controller = new SearchedUserController());
        try {
            view = fxmlLoader.load();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        getChildren().add(view);
    }

    public void setUsuario(Usuario us) {
        controller.setUser(us);
        controller.txtUsername.setText(us.getAlias());
    }
}
