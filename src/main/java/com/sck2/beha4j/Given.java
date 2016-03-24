package com.sck2.beha4j;

@FunctionalInterface
public interface Given {
    
    default Given given(final String name, final Given given) {
      given.execute(name);
        return given;
    }

    default When when(final String name, final When when) {
      when.execute(name);
      return when;
    }

    public void execute(String name);
}