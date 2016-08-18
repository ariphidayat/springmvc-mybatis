package org.arip.util;

import java.beans.PropertyVetoException;

/**
 * Created by Arip Hidayat on 12/08/2016.
 */
public interface TargetRegistry {

    Object getTarget(final String context) throws PropertyVetoException;
}
