package test.com.sample;

import org.junit.Test;

import com.sample.Greetable;
import com.sample.Greeter;
import com.sample.MyTransformation;

public class ByteBuddyTester {

    @Test
    public void whenAdvice() {

        MyTransformation.getInstance();

        final Greetable g1 = new Greeter();
        g1.say("001");
    }
}
