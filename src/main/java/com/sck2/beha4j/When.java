package com.sck2.beha4j;

import static com.sck2.beha4j.Results.RESULTS;

@FunctionalInterface
public interface When {

  default Then then(final String name, final Then then) {
    try {
      then.execute(name);
      RESULTS.passThen(name);
    } catch (Throwable e) {
      RESULTS.failThen(name, e);
    }
    return then;
  }

  public void execute(String name);
}
