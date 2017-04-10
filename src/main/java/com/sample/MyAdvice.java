package com.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.bytebuddy.asm.Advice;

public class MyAdvice {


    /**
     * Intercept when entering the method by starting the profiler.
     *
     * @param declaredType
     *            The intercepted class name as a profiler name
     * @param methodName
     *            The intercepted method name as a stopwatch name.
     * @since 0.0.1
     * @see Advice
     * @see Advice.OnMethodEnter
     */
    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin("#t")
                             final String declaredType,
                             @Advice.Origin("#m")
                             final String methodName) {
        MyAdvice.getLogger().info("ENTER: {} - {}",
                                   declaredType,
                                   methodName);

    }

    /**
     * Intercept when exiting the method by stopping the profiler.
     *
     * @param declaredType
     *            The intercepted class name as a marker name
     * @since 0.0.1
     * @see Advice
     * @see Advice.OnMethodExit
     */
    @Advice.OnMethodExit
    public static void exit(@Advice.Origin("#t")
                            final String declaredType,
                            @Advice.Origin("#m")
                            final String methodName) {
        MyAdvice.getLogger().info("EXIT: {} - {}",
                                  declaredType,
                                  methodName);
    }

    public static Logger getLogger() {
        return LoggerFactory.getLogger(MyAdvice.class);
    }
}
