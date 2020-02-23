package com.sck2.beha4j;

import static com.sck2.beha4j.Results.RESULTS;

public class Beha4j {

  public static Beha4j scenario(final String name) {
    RESULTS.passScenario(name);
    return new Beha4j();
  }
  
  public Given given(final String name, Given given) {
    try {
      given.execute(name);
      RESULTS.passGiven(name);
    } catch (RuntimeException e) {
      RESULTS.failGiven(name, e);
      throw e;
    }
    return given;
  }
}
