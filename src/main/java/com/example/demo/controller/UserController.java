package com.example.demo.controller;

import com.example.demo.controller.Utils.RenderTableDeleteButton;
import com.example.demo.controller.Utils.Render;
import com.example.demo.dto.StudentReq;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.google.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class UserController implements Initializable {

  @FXML
  public StackPane UserMainView;
  @FXML
  public VBox StudentTableView;
  @FXML
  public VBox AddStudentModal;

  @FXML
  public TextField nameField;
  @FXML
  public TextField matricField;
  @FXML
  public TextField departmentField;
  @FXML
  public Label errorMessage;
  @Inject
  StudentService studentService;
  @FXML
  private TableView<Student> studentsTable = new TableView<>();
  @FXML
  public TableColumn<Student, String> nameColumn;
  @FXML
  public TableColumn<Student, String> matricColumn;
  @FXML
  public TableColumn<Student, String> deptColumn;

  @FXML
  public TableColumn<Student, Void> deleteColumn;

  Render renderAddStudentModal = null;
  Render renderStudentView = null;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    renderStudentView = Render.builder()
        .viewToFront(StudentTableView)
        .viewToBack(AddStudentModal).build();

    renderAddStudentModal = Render.builder()
        .viewToFront(AddStudentModal)
        .viewToBack(StudentTableView)
        .build();

    initStudentTable();
  }

  private void initStudentTable() {
    studentService.fakeStudents();
    renderStudentView.renderPage();
    refreshStudentTable();

    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    matricColumn.setCellValueFactory(new PropertyValueFactory<>("matricNumber"));
    deptColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
    deleteColumn.setCellFactory(column -> new RenderTableDeleteButton<>((student) -> {
      studentService.deleteStudent(student.getId());
      refreshStudentTable();
      return null;
    }));
  }

  private void refreshStudentTable() {
    studentsTable.setItems(
        FXCollections.observableArrayList(studentService.getStudents())
    );
  }

  @FXML
  public void addStudentButtonClicked(ActionEvent actionEvent) {
    errorMessage.setText("");
    renderAddStudentModal.renderPage();
  }

  @FXML
  public void submitStudentButtonClicked(ActionEvent actionEvent) {
    if (nameField.getText().isEmpty() || departmentField.getText().isEmpty()
        || matricField.getText().isEmpty()) {
      errorMessage.setText("Please fill all fields!");
      return;
    }

    studentService.addStudent(StudentReq.builder()
        .name(nameField.getText().trim())
        .matricNumber(matricField.getText().trim())
        .department(departmentField.getText().trim())
        .build());

    clearFields();
    refreshStudentTable();
    errorMessage.setText("Student Added !");
  }

  private void clearFields() {
    nameField.setText("");
    matricField.setText("");
    departmentField.setText("");
  }

  @FXML
  public void closeAddStudentModal(ActionEvent actionEvent) {
    renderStudentView.renderPage();
  }

 }