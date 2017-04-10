package test.com.sample;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    ByteBuddyTester.class
})
public class ByteBuddyTestSuite {

}
