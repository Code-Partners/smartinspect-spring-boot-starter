package com.codepartners.smartinspect.logexecutionflow;

import com.gurock.smartinspect.session.Session;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class LogExecutionFlowAspect {
    private final Session session;

    public LogExecutionFlowAspect(Session session) {
        this.session = session;
    }

    @Around("@within(LogExecutionFlow) || @annotation(LogExecutionFlow)")
    public Object logMethodFlow(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature sig = (MethodSignature) pjp.getSignature();
        String method = sig.getDeclaringType().getSimpleName() + "." + sig.getName();

        session.enterMethod(method);
        try {
            return pjp.proceed();
        } finally {
            session.leaveMethod(method);
        }
    }
}