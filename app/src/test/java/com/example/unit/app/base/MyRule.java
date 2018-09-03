package com.example.unit.app.base;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static com.example.unit.app.base.PrintUtil.print;

/**
 * @author lisen
 * @since 09-03-2018
 */

public class MyRule implements TestRule {
    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                String methodName = description.getMethodName();
                String className = description.getClassName();

                print("start test " + className + " " + methodName);

                base.evaluate();

                print("end test " + className + " " + methodName);

            }
        };
    }
}
