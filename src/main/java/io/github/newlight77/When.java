package io.github.newlight77;

import static io.github.newlight77.Results.RESULTS;

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
