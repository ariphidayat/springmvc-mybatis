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

    public ContextSwappableTargetSource(Class targetClass) {
        super();
        this.targetClass = targetClass;
    }

    public final Class getTargetClass() {
        return targetClass;
    }

    public final boolean isStatic() {
        return false;
    }

    public Object getTarget() throws Exception {
        String context = ContextHolder.getContext();
        logger.debug("Current context: '{}'", context);

        Object target = registry.getTarget(context);

        return target;
    }

    public void releaseTarget(Object target) throws Exception {

    }

    public final void afterPropertiesSet() throws Exception {

    }

    public final void setTargetRegistry(final TargetRegistry registry) {
        this.registry = registry;
    }
}
