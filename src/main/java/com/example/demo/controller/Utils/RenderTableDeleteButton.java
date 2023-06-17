package com.example.demo.controller.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.util.Callback;

public class RenderTableDeleteButton<T> extends TableCell<T, Void> {
  private final Button deleteButton = new Button("Delete");


  public RenderTableDeleteButton(Callback<T, Void> deleteActionCallback) {
    deleteButton.getStyleClass().add("red-button");
    deleteButton.setOnAction(event -> {
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setContentText("Delete?");

      ButtonType buttonTypeYes = new ButtonType("Yes");
      ButtonType buttonTypeNo = new ButtonType("No");
      alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

      alert.showAndWait().ifPresent(buttonType -> {
        if (buttonType == buttonTypeYes) {
          T item = getTableView().getItems().get(getIndex());
          deleteActionCallback.call(item);
        } else if (buttonType == buttonTypeNo) {
          alert.close();
        }
      });
    });
  }

  @Override
  protected void updateItem(Void item, boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
      setGraphic(null);
    } else {
      setGraphic(deleteButton);
    }
  }
}
