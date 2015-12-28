package com.codemagic.magica.hoster.common.util;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;

public class ExceptionUtil {
	public static void wrapAndThrowAsServiceHosterException(Throwable cause) throws ServiceHosterException {
		if (cause instanceof ServiceHosterException) {
			throw (ServiceHosterException) cause;
		} else {
			throw new ServiceHosterException(cause);
		}
	}

	public static void throwAsServiceHosterException(String message) throws ServiceHosterException {
		throw new ServiceHosterException(message);
	}

}
