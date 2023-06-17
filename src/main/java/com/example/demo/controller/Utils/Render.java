package com.example.demo.controller.Utils;

import javafx.scene.layout.Pane;
import lombok.Builder;

@Builder
public class Render {

  Pane viewToFront;
  Pane viewToBack;

  public void renderPage(){
    viewToFront.toFront();
    viewToBack.toBack();
  }
}
