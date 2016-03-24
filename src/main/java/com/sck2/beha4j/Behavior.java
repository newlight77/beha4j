package com.sck2.beha4j;

@FunctionalInterface
public interface Behavior {

  public static Given given(final String name, final Given given) {
    return given;
  }

  public Behavior given(String description, Behavior Behavior);

}
