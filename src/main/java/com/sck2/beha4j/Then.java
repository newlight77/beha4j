package com.sck2.beha4j;

import java.util.Optional;

import static com.sck2.beha4j.Results.RESULTS;

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

    default void complete() throws Throwable {
        RESULTS.results().values().stream()
                .filter(Results.Result::isFailed)
                .findFirst()
                .ifPresent(result -> {
                    String scenario = RESULTS.results().values().iterator().next().getFailedStatement();
                    RESULTS.failScenario(scenario, new Throwable());
                });

        RESULTS.results().forEach((s, result) -> {
            System.out.println(s + " : " + result.outcome());
        });

        Optional<Results.Result> r = RESULTS.results().values().stream()
                .filter(Results.Result::isFailed)
                .skip(1).findFirst();

        if (r.isPresent()) {
            r.get().throwable().printStackTrace();
            System.out.println(r.get().throwable().getLocalizedMessage());
            throw r.get().throwable();
        }
    }

    public abstract void execute(String name);
}
