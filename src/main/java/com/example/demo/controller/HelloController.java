package com.example.demo.controller;

import com.example.demo.controller.Utils.Render;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.google.inject.Inject;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HelloController implements Initializable {

  @FXML
  public AnchorPane UserPage;
  @FXML
  public VBox LoginPage;
  @FXML
  public StackPane MainPage;
  @FXML
  public PasswordField passwordField;
  @FXML
  public TextField emailField;

  @FXML
  public Label message;

  @Inject
  UserRepository userRepository;

  Render renderMainPage = null;
  Render renderLoginPage = null;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    renderLoginPage = Render.builder().viewToFront(LoginPage).viewToBack(MainPage).build();
    renderMainPage =  Render.builder().viewToFront(MainPage).viewToBack(LoginPage).build();

    userRepository.fakeUsers();
    renderLoginPage.renderPage();
  }

  @FXML
  public void LoginClicked(ActionEvent actionEvent) {
    message.setText("");

    if (emailField.getText().isEmpty()) {
      message.setText("The Email must be provided");
      return;
    }
    if (passwordField.getText().isEmpty()) {
      message.setText("The Password must be provided");
      return;
    }

    User user = userRepository.findByEmail(emailField.getText())
        .orElse(null);

    if (Objects.isNull(user)) {
      message.setText("The User is not found");
      return;
    }

    if (!user.getPassword().equals(passwordField.getText())) {
      message.setText("The Login credentials are incorrect");
      return;
    }
    renderMainPage.renderPage();
  }
}