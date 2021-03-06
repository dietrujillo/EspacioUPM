package espacioUPM.App;

import espacioUPM.UI.Perfil;
import espacioUPM.UI.TimelineController;
import espacioUPM.Usuarios.IUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable, IMainControllerUtils, IMainControllerScene {

    private static Stage stage;
    private static IUsuario thisUser;
    private static MainController instance;

    /* Principal */
    @FXML BorderPane borderPaneMain;
    @FXML Button btnTimeline;
    @FXML Button btnProfile;
    @FXML Button btnSearch;
    @FXML Button btnCommunities;
    @FXML Button btnSettings;
    @FXML Button btnNew;

    static BorderPane sBorderPane;

    private static String currentComponent = "/TimelinePage.fxml";
    private static Node currentComponentNode = null;
    private static boolean isNodeActive = false;

    public MainController() {}

    public static MainController getInstance() {
        if(instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public void setStage(Stage stage) {
        MainController.stage = stage;
    }

    public IUsuario getThisUser() { return thisUser; }

    public void setThisUser(IUsuario value) { thisUser = value; }

    public void setTitle(String txt) {
        stage.setTitle(txt);
    }

    public Parent replaceScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxml));
        Parent root = loader.load();
        if (stage == null) {
            System.out.println("[-] Stage no cargada - Algo ha ido mal");
            return null;
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/fextile.css");
        stage.setScene(scene);
        stage.sizeToScene();
        System.out.println("[+] Nueva escena: " + fxml);
        if(fxml.equals("/LandingPage.fxml")) setTitle("EspacioUPM");
        return root;
    }

    public void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.show();
    }

    public <T> T replaceComponent(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxml));
            //(borderPaneMain != null ? borderPaneMain : sBorderPane).setCenter(loader.load());
            HBox root = (HBox)(borderPaneMain != null ? borderPaneMain : sBorderPane).getCenter();
            root.getChildren().clear();
            root.getChildren().add(loader.load());

            currentComponent = fxml;
            isNodeActive = false;
            System.out.println("[+] Nueva subescena: " + fxml);
            stage.sizeToScene();
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void replaceComponent(Node node) {
        currentComponentNode = node;
        isNodeActive = true;
        HBox root = (HBox) (borderPaneMain != null ? borderPaneMain : sBorderPane).getCenter();
        root.getChildren().clear();
        root.getChildren().add(node);
        System.out.println("[+] Nueva subescena que depende de una instancia\n" +
                "Es un perfil, una comunidad o una lista de usuarios");
        stage.sizeToScene();
    }

    public <T> T refresh() {
        if(isNodeActive)
            replaceComponent(currentComponentNode);
        else return replaceComponent(currentComponent);

        return null;
    }


    public void onBtnTimelineClick(ActionEvent actionEvent) {
        TimelineController.setNumPagina(0);
        replaceComponent("/TimelinePage.fxml");
    }

    public void onBtnProfileClick(ActionEvent actionEvent) {
        Perfil p = new Perfil();
        p.setPerfil(thisUser);
        replaceComponent(p);
    }

    public void onBtnCommunitiesClick(ActionEvent actionEvent) {
        replaceComponent("/CommunityPage.fxml");
    }

    public void onBtnSearchClick(ActionEvent actionEvent) {
        replaceComponent("/SearchPage.fxml");
    }

    public void onBtnSettingsClick(ActionEvent actionEvent) {
        replaceComponent("/SettingsPage.fxml");
    }

    public void onBtnNewClick(ActionEvent actionEvent) {
        replaceComponent("/NewTweetPage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sBorderPane = borderPaneMain;
        replaceComponent("/TimelinePage.fxml");
    }
}
