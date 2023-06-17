package com.example.demo.core;


import com.google.inject.Guice;
import com.google.inject.Injector;

public class AppInjector {
  private static Injector injector;

  public static Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(new AppModule());
    }
    return injector;
  }
}
