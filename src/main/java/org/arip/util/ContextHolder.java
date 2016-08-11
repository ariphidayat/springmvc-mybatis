package org.arip.util;

import org.slf4j.LoggerFactory;

/**
 * Created by Arip Hidayat on 11/08/2016.
 */
public class ContextHolder {

    private static final ThreadLocal holder = new ThreadLocal();

    public static void setContext(String context) {
        LoggerFactory.getLogger(ContextHolder.class).debug("context set '{}'", context);
        holder.set(context);
    }

    public static String getContext() {
        return (String) holder.get();
    }
}
