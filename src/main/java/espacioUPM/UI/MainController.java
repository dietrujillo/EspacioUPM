package espacioUPM.UI;

import espacioUPM.App;
import espacioUPM.Database.DB_Main;
import espacioUPM.Database.IDB_PasswordHandler;
import espacioUPM.Database.IDB_Usuario;
import espacioUPM.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static Stage mStage;
    @FXML TextField txtAlias;
    @FXML PasswordField txtPass;

    /* Registro */
    @FXML TextField txtMail;
    @FXML PasswordField txtRegPass;

    static final IDB_Usuario DB_user = DB_Main.getInstance();
    static final IDB_PasswordHandler DB_pass = DB_Main.getInstance();

    public MainController() {}

    public MainController(Stage s) {
        mStage = s;
    }

    public void setStage(Stage stage) {
        this.mStage = stage;
    }

    public Parent replaceScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxml));
        Parent root = loader.load();
        if (mStage == null) {
            System.out.println("[-] Ouch");
            return null;
        }
        Scene scene = new Scene(root);
        mStage.setScene(scene);
        mStage.sizeToScene();
        return root;
    }

    public void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.show();
    }

    @FXML
    public void onBtnLoginClick(ActionEvent actionEvent) {
        Usuario usuario = DB_user.getUsuario(txtAlias.getText());

        if (txtAlias.getText().isEmpty() || txtPass.getText().isEmpty()) {
            alert("Por favor rellene ambos campos.");
        }
        else if (usuario == null) {
            alert("El usuario especificado no existe.");
        }
        else if (!DB_pass.comprobarPasswd(txtAlias.getText(), txtPass.getText())) {
            System.out.println("contraseña incorrecta");
            alert("Contraseña incorrecta.");
        } else {
            try {
                App.thisUser = usuario;
                replaceScene("/TimelinePage.fxml");
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    public void onBtnRegisterClick(ActionEvent actionEvent) {
        try {
            mStage.setTitle("| Registro |");
            replaceScene("/RegisterPage.fxml");
        }catch(NullPointerException | IOException e) {
            System.out.println("oops");
        }
    }

    public void onBtnRegSendClick(ActionEvent actionEvent) {
        Random r = new Random();
        byte[] salt = new byte[16];
        r.nextBytes(salt);
        if (DB_user.setUsuario(txtMail.getText().split("@")[0], txtMail.getText(), txtRegPass.getText().getBytes(), salt)) {
            mStage.setTitle("| Login |");
            try {
                replaceScene("/LandingPage.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            alert("Fallo al registrarse");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
