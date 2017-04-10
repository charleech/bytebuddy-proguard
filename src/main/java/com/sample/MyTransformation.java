package com.sample;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy;
import net.bytebuddy.agent.builder.ResettableClassFileTransformer;
import net.bytebuddy.matcher.ElementMatchers;

public class MyTransformation {

    private static final MyTransformation INSTANCE = new MyTransformation();

    private final AgentBuilder bldr;

    private ResettableClassFileTransformer resetter;

    public static MyTransformation getInstance() {
        return MyTransformation.INSTANCE;
    }

    private MyTransformation() {
        ByteBuddyAgent.install();

        this.bldr = new AgentBuilder.Default(new ByteBuddy()).
                                     ignore(ElementMatchers.none()).
                                     with(AgentBuilder.Listener.
                                                       StreamWriting.
                                                       toSystemOut()).
                                     with(RedefinitionStrategy.
                                             RETRANSFORMATION).
                                     disableClassFormatChanges().
                                     type(ElementMatchers.isSubTypeOf(
                                             Greetable.class)).
                                     transform(
                                         new AgentBuilder.Transformer.ForAdvice().
                                             advice(ElementMatchers.not(ElementMatchers.isAbstract()),
                                                    MyAdvice.class.getName())
                                     );
        this.resetter = this.bldr.installOnByteBuddyAgent();
    }

    public void reset() {
        this.resetter.reset(ByteBuddyAgent.getInstrumentation(),
                            RedefinitionStrategy.RETRANSFORMATION);
    }

    public void reApply() {
        this.resetter = this.bldr.installOn(ByteBuddyAgent.
                                                getInstrumentation());
    }
}
