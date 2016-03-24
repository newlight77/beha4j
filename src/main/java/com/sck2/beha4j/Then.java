package com.sck2.beha4j;

@FunctionalInterface
public interface Then {
  
    default When then(final String name, final When when) {
      when.execute(name);
      return when;
    }

    public abstract void execute(String name);
}
