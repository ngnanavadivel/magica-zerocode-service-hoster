package com.codemagic.magica.hoster.service;

import org.springframework.stereotype.Component;

import com.codemagic.magica.hoster.common.exception.ServiceHosterException;

@Component
public interface DataUnMarshaller {
   Object convertToPOJO(String data, String packageWhereTypeExists, String className, ClassLoader classLoader)
         throws ServiceHosterException;
}
