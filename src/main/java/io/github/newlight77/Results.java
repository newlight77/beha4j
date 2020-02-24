package io.github.newlight77;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public enum Results {
    RESULTS;

    private final static ThreadLocal<Map<String, Result>> results = ThreadLocal.withInitial(LinkedHashMap::new);

    public Map<String, Result> results() {
        return results.get();
    }

    public void print(Printer printer) throws Throwable {
        results().forEach((s, result) -> {
            printer.print(s + " : " + result.outcome());
        });

        Optional<Result> scenarioResult = results().values().stream()
                .filter(Results.Result::isFailed)
                .skip(1).findFirst();

        if (scenarioResult.isPresent()) {
            printer.print(scenarioResult.get().throwable().getLocalizedMessage());
            throw scenarioResult.get().throwable();
        }
    }

    public void failScenario(String name, Throwable e) {
        results.get().put("Scenario : " + name, new Result().fail(name, e));
    }

    public void passScenario(String name) {
        results.get().put("Scenario : " + name, new Result().pass(name));
    }

    public void failGiven(String name, Throwable e) {
        results.get().put("Given : " + name, new Result().fail(name, e));
    }

    public void passGiven(String name) {
        results.get().put("Given : " + name, new Result().pass(name));
    }

    public void failWhen(String name, Throwable e) {
        results.get().put("When : " + name, new Result().fail(name, e));
    }

    public void passWhen(String name) {
        results.get().put("When : " + name, new Result().pass(name));
    }

    public void failThen(String name, Throwable e) {
        results.get().put("Then : " + name, new Result().fail(name, e));
    }

    public void passThen(String name) {
        results.get().put("Then : " + name, new Result().pass(name));
    }

    public class Result {
        private boolean failed = false;
        private String failedStatement = "";
        private Throwable throwable;

        Result fail(String name, Throwable e) {
            failed = true;
            failedStatement = name;
            throwable = e;
            return this;
        }

        Result pass(String name) {
            failed = false;
            failedStatement = name;
            return this;
        }

        public boolean isFailed() {
            return failed;
        }

        public Throwable throwable() {
            return throwable;
        }

        public String outcome() {
            return failed ? "failed" : "passed";
        }

        public String getFailedStatement() {
            return failedStatement;
        }
    }
}
