package org.arip.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.TargetSource;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by Arip Hidayat on 12/08/2016.
 */
public class ContextSwappableTargetSource implements TargetSource, InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(ContextSwappableTargetSource.class);

    private TargetRegistry registry;
    private Class targetClass;
    private boolean alwaysReturnTarget = false;
    private Object defaultTarget;

    public ContextSwappableTargetSource(Class targetClass) {
        super();
        this.targetClass=targetClass;
    }

    public Object getTarget() throws Exception {
        String contextName = ContextHolder.getContext();
        logger.debug("Current context: '{}'", contextName);

        Object target = getTarget(contextName);

        if (target == null) {
            logger.error("Cannot locate a target of type '{}' for context '{}'", targetClass.getName(), contextName);
            throw new TargetLookupFailureException("Cannot locate a target for context '"+contextName+"'");
        }

        if (!targetClass.isAssignableFrom(target.getClass())) {
            throw new TargetLookupFailureException("The target for '"+contextName+"' is not of the required type." +
                    "Expected '"+targetClass.getName()+"' and got '"+target.getClass().getName()+"'");
        }

        return target;

    }

    public final Class getTargetClass() {
        return targetClass;
    }

    public final boolean isStatic() {
        return false;
    }

    private Object getTarget(final String context) {
        Object target = registry.getTarget(context);
        if (target == null && alwaysReturnTarget) {
            logger.debug("Return default target for context '{}'", context);
            target = defaultTarget;
        }
        return target;
    }

    public void releaseTarget(Object target) throws Exception {
        this.registry = null;
    }

    public final void afterPropertiesSet() throws Exception {

    }

    public final void setTargetRegistry(final TargetRegistry registry) {
        this.registry=registry;
    }
}
