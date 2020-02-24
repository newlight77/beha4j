package io.github.newlight77;

import static io.github.newlight77.Results.RESULTS;

@FunctionalInterface
public interface Then {
  
    default Then then(final String name, final Then then) {
        try {
            then.execute(name);
            RESULTS.passThen(name);
        } catch (Throwable e) {
            RESULTS.failThen(name, e);
            throw e;
        }
      return then;
    }

    default void complete(Printer printer) throws Throwable {
        RESULTS.results().values().stream()
                .filter(Results.Result::isFailed)
                .findFirst()
                .ifPresent(result -> {
                    String scenario = RESULTS.results().values().iterator().next().getFailedStatement();
                    RESULTS.failScenario(scenario, new Throwable());
                });

        RESULTS.print(printer);
    }

    void execute(String name);
}
