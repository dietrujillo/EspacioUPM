package espacioUPM.UI;

import espacioUPM.App.IMainControllerScene;
import espacioUPM.App.IMainControllerUtils;
import espacioUPM.Usuarios.IUsuario;
import espacioUPM.Usuarios.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PerfilController implements Initializable {

    @FXML Button btnFollow;
    @FXML Label txtUsername;
    @FXML Label txtSeguidores;
    @FXML Label txtSeguidos;
    @FXML ScrollPane scrollPanePublis;


    private static final IMainControllerUtils maincontroller = IMainControllerUtils.getInstance();
    private static final IMainControllerScene maincontrollerScene = IMainControllerScene.getInstance();

    private IUsuario usuario;
    private static boolean estaSiguiendo = false;

    public Label getTxtUsername() {
        return txtUsername;
    }

    public Button getBtnFollow() {
        return btnFollow;
    }

    public void setEstaSiguiendo(boolean estaSiguiendo) {
        PerfilController.estaSiguiendo = estaSiguiendo;
    }

    public boolean getEstaSiguiendo() {
        return estaSiguiendo;
    }

    public ScrollPane getScrollPanePublis() {
        return scrollPanePublis;
    }

    public void onClickFollow(ActionEvent actionEvent) {
        if (estaSiguiendo) {
            maincontroller.getThisUser().dejarDeSeguir(usuario.getAlias());
            btnFollow.setText("Seguir");
        }
        else {
            maincontroller.getThisUser().seguir(usuario.getAlias());
            btnFollow.setText("Dejar de seguir");
        }
        estaSiguiendo = !estaSiguiendo;
        maincontrollerScene.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtUsername.requestFocus();
    }

    public void setUsuario(IUsuario usuario) {
        this.usuario = usuario;
        estaSiguiendo = maincontroller.getThisUser().sigueA(usuario.getAlias());
        if(estaSiguiendo)
            btnFollow.setText("Dejar de seguir");
        else
            btnFollow.setText("Seguir");
        txtSeguidores.setText("Seguidores: " + usuario.getSeguidores().length);
        txtSeguidos.setText("Seguidos: " + usuario.getSeguidos().length);
    }

    public void onClickSeguidores(MouseEvent event) {
        ListaUsuarios l = new ListaUsuarios();
        String[] users = usuario.getSeguidores();
        IUsuario[] users2 = new IUsuario[users.length];
        for(int i = 0; i < users.length; i++) {
            users2[i] = new Usuario(users[i]);
        }
        l.setData(users2, usuario);
        maincontrollerScene.replaceComponent(l);
    }

    public void onClickSeguidos(MouseEvent event) {
        ListaUsuarios l = new ListaUsuarios();
        String[] users = usuario.getSeguidos();
        IUsuario[] users2 = new IUsuario[users.length];
        for(int i = 0; i < users.length; i++) {
            users2[i] = new Usuario(users[i]);
        }
        l.setData(users2, usuario);
        maincontrollerScene.replaceComponent(l);
    }
}
