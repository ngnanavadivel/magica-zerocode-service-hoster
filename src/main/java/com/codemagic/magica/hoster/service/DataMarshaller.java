package com.codemagic.magica.hoster.service;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;

public interface DataMarshaller {
   public String serializeToString(Object pojo) throws ServiceHosterException;
}
