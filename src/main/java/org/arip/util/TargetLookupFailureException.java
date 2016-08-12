package org.arip.util;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by Arip Hidayat on 12/08/2016.
 */
public class TargetLookupFailureException extends NestedRuntimeException {

    public TargetLookupFailureException(String message) {
        super(message);
    }
}
