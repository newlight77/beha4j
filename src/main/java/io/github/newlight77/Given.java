package io.github.newlight77;

import static io.github.newlight77.Results.RESULTS;

@FunctionalInterface
public interface Given {
    
    default Given given(final String name, final Given given) {
        try {
            given.execute(name);
            RESULTS.passGiven(name);
        } catch (Throwable e) {
            RESULTS.failGiven(name, e);
            throw e;
        }
        return given;
    }

    default When when(final String name, final When when) {
        try {
            when.execute(name);
            RESULTS.passWhen(name);
        } catch (Throwable e) {
            RESULTS.failWhen(name, e);
            throw e;
        }
        return when;
    }

    void execute(String name);
}
