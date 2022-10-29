package bookings.controllers;

import bookings.models.User;
import bookings.models.UserDAO;
import bookings.util.Views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    public Label msg;

    @FXML
    public Hyperlink registerHL;

    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;


    @FXML
    public void onLoginBtnClicked() throws IOException, SQLException, ClassNotFoundException {

        User user = UserDAO.login(usernameTF.getText(), passwordTF.getText());

        if (user == null) {
            msg.setText("Wrong Password Or Username");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Views.MAIN));
        loader.load();
        BorderPane root = loader.getRoot();

        MainController mainController = loader.getController();
        mainController.setRoot(root);
        mainController.setUser(user);

        ((Stage) usernameTF.getScene().getWindow()).close();
    }

}