# beha4j

# Purpose

This library could be used to help to tackle BDD approach, it does not do anything special othewise.



```java
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
            .complete();

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
            .complete();

    }

}
```

The outcome would be :

```java
Scenario : Failing to merging 2 lists of string : failed
Given : there is a list with 3 string : passed
Given : there is a second list with 2 string : passed
When : the 2 lists are merged : passed
Then : this resulting list has 5 strings : failed

Expected size:<5> but was:<6> in:
<["test1", "test2", "test3", "test3", "test4", "test5"]>


java.lang.AssertionError: 
Expected size:<5> but was:<6> in:
<["test1", "test2", "test3", "test3", "test4", "test5"]>

	at io.github.newlight77.Beha4jExample.lambda$failingTest$7(Beha4jExample.java:60)
	at io.github.newlight77.When.then(When.java:10)
	at io.github.newlight77.Beha4jExample.failingTest(Beha4jExample.java:59)


Scenario : Failing to merging 2 lists of string : failed
Given : there is a list with 3 string : passed
Given : there is a second list with 2 string : passed
When : the 2 lists are merged : passed
Then : this resulting list has 5 strings : passed
Scenario : Successfully merging 2 lists of string : passed

```

