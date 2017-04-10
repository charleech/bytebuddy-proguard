package com.sample;

@ToString
public class Greeter implements Greetable {

    @Override
    public String say(final String name) {
        try {
            return "Hello " + name + ".";
        } catch (final Exception e) {
            return e.getMessage();
        }
    }

}
