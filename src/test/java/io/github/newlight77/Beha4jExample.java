package io.github.newlight77;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class Beha4jExample {

    @Test
    public void passingTest() throws Throwable {

        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        Beha4j
            .scenario("Successfully merging 2 lists of string")
            .given("there is a list with 3 string", name -> {
                list.add("test1");
                list.add("test2");
                list.add("test3");
            })
            .given("there is a second list with 2 string", name -> {
                list2.add("test4");
                list2.add("test5");
            })
            .when("the 2 lists are merged", name -> {
                list.addAll(list2);
            })
            .then("this resulting list has 5 strings", name -> {
                Assertions.assertThat(list).hasSize(5);
            })
            .complete(Printer::printConsole);

    }

    @Test
    public void failingTest() throws Throwable {

        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        Beha4j
            .scenario("Failing to merging 2 lists of string")
            .given("there is a list with 3 string", name -> {
                list.add("test1");
                list.add("test2");
                list.add("test3");
            })
            .given("there is a second list with 2 string", name -> {
                list2.add("test3");
                list2.add("test4");
                list2.add("test5");
            })
            .when("the 2 lists are merged", name -> {
                list.addAll(list2);
            })
            .then("this resulting list has 5 strings", name -> {
                Assertions.assertThat(list).hasSize(5);
            })
            .complete(Printer::printConsole);

    }

}
