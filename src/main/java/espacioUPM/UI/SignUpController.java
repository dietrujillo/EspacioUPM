package espacioUPM.UI;

import espacioUPM.Database.DB_Main;
import espacioUPM.Database.IDB_PasswordHandler;
import espacioUPM.Database.IDB_Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpController {

    private static IDB_Usuario DB = DB_Main.getInstance();
    private static IDB_PasswordHandler DB_pass = DB_Main.getInstance();
    private static MainController controller = MainController.getInstance();

    /* Registro */
    @FXML
    TextField txtAlias;
    @FXML
    TextField txtMail;
    @FXML
    PasswordField txtRegPass;
    @FXML
    PasswordField txtRegPassRepeat;
    @FXML
    Button btnSendReg;
    @FXML
    Button btnBack;

    public void onBtnRegSendClick(ActionEvent actionEvent) {
        String alias = txtAlias.getText();
        String correo = txtMail.getText();
        String password = txtRegPass.getText();
        String password2 = txtRegPassRepeat.getText();
        if(alias.isEmpty() || correo.isEmpty() ||
        password.isEmpty() || password2.isEmpty()) {
            controller.alert("Por favor, rellene todos los campos");
        }
        else if(DB.getUsuario(alias) != null) {
            controller.alert("El alias pedido ya existe");
        }
        // TODO: comprobar el correo. Hay tema de conectarse con el servidor de la UPM
        //y a lo mejor es demasiado para hacer. Yo lo dejo aquí por si acaso luego nos sobra
        //el tiempo.
        //else if() {}
        else if(!password.equals(password2)) {
            controller.alert("Las contraseñas no coinciden");
        }
        else {
            DB.setUsuario(alias, correo, password);
            try {
                controller.replaceScene("/LandingPage.fxml");
            }
            catch(IOException e) { e.printStackTrace(); }
        }
    }

    public void onBtnBack(ActionEvent actionEvent) {
        try{
            controller.replaceScene("/LandingPage.fxml");
        }
        catch(IOException e) { e.printStackTrace(); }
    }

}
