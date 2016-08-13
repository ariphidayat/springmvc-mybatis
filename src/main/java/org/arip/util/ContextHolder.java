package org.arip.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Arip Hidayat on 11/08/2016.
 */
public class ContextHolder {

    private static final Logger logger = LoggerFactory.getLogger(ContextHolder.class);
    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static void setContext(String context) {
        logger.debug("Context set to '{}'", context);
        holder.set(context);
    }

    public static String getContext() {
        return holder.get();
    }
}
