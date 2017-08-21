package com.anya.own.handler;

import com.anya.own.common.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wuwenliang
 * @date 2017-08-12.
 */
public class InOutLogHandler {

    private static final Logger logger = LoggerFactory.getLogger(InOutLogHandler.class);

    public Object handleDubboMethod(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        Object[] args = pjp.getArgs();
        long startTime = System.currentTimeMillis();
        traceRequestLog(signature.toString(), args);
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        traceResultLog(signature.toString(), result, endTime - startTime);

        return result;
    }

    private void traceResultLog(String name, Object result, long l) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        if (result == null) {
            logger.info(String.format("\r\n【响应】 %s\r\n耗时：%d\r\n 无返回参数\r\n", name, l));
        }
        else {
            logger.info(String.format("\r\n【响应】 %s\r\n耗时：%d\r\n 返回参数 %s\r\n", name, l, JsonUtil.toJson(result)));
        }
    }

    private void traceRequestLog(String methodName, Object[] args) {
        if (!logger.isDebugEnabled()) {
            return;
        }
        String log = "\r\n【请求】SOA方法： " + methodName;
        if (args != null && args.length > 0) {
            Object arg = args[0];
            log += " 请求参数：\r\n" + JsonUtil.toJson(arg) + "\r\n";
        }

        logger.info(log);
    }
}
