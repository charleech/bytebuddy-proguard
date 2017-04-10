package it.test.com.sample;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sample.Greeter;
import com.sample.MyTransformation;

@RunWith(CdiTestRunner.class)
public class ByteBuddyIntgrtnTester {

    @Inject
    private Greeter greeter;

    @Test
    public void whenAdvice() {

        MyTransformation.getInstance();

        this.greeter.say("001");
    }
}
