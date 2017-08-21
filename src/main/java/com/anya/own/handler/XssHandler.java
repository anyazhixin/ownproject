package com.anya.own.handler;

import com.anya.own.dto.Request;
import org.apache.commons.lang3.StringEscapeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wuwenliang
 * @date 2017-08-16.
 */
public class XssHandler {

    private static final Logger logger = LoggerFactory.getLogger(XssHandler.class);

    public Object cleanXss(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof Request) {
                Object data = ((Request) arg).getData();
                if (data instanceof String) {
                    String log = "入参：" + data;
                    data = StringEscapeUtils.escapeHtml4((String) data);
                    logger.debug(log + " ,转义后：" + data);
                    ((Request) arg).setData(data);
                }
                else {
                    Class<?> dataClass = data.getClass();
                    Field[] fields = dataClass.getDeclaredFields();
                    for (Field field : fields) {
                        if (field.getType() == String.class) {
                            String fieldName = field.getName();
                            String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
                            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
                            Method setMethod = dataClass.getMethod(setMethodName, String.class);
                            Method getMethod = dataClass.getMethod(getMethodName);
                            String beforeValue = (String) getMethod.invoke(data);
                            String log = "入参：" + beforeValue;
                            String value = StringEscapeUtils.escapeHtml4(beforeValue);
                            setMethod.invoke(data, value);
                            logger.debug(log + " ,转义后：" + value);
                        }
                    }
                }
            }
        }
        Object result = pjp.proceed(args);

        return result;
    }
}
