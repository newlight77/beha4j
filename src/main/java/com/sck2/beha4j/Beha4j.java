package com.sck2.beha4j;

public class Beha4j {

  public static Beha4j scenario(final String name) {
    System.out.println(name);
    return new Beha4j();
  }
  
  public Given given(final String name, Given given) {
    given.execute(name);
    return given;
  }
  
}
