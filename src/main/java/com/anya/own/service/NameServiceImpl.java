package com.anya.own.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.anya.own.biz.NameBusiness;
import com.anya.own.common.dao.DataSourceHolder;
import com.anya.own.dto.Request;
import com.anya.own.dto.Result;
import com.anya.own.dto.test.GetUserRequest;
import com.anya.own.dto.test.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wuwenliang
 * @date 2017-08-04.
 */
@Service
public class NameServiceImpl implements NameService {

    private static final Logger logger = LoggerFactory.getLogger(NameServiceImpl.class);

    @Autowired
    private NameBusiness business;

    @Override
    public Result<UserDTO> getUser(Request<GetUserRequest> request) {
        Result<UserDTO> result = new Result<>();

        logger.debug("getName入参：" + request.getData().getName());

        try {
            DataSourceHolder.setCurrentDataSource("dd");
            UserDTO user = business.getUser(request.getData());
            result.success(user);
        } catch (Exception e) {
            logger.error("getName异常：", e);
            result.fail("1001", e.toString());
        }

        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = new Jedis("localhost");
        jedis.select(1);
        jedis.setex("name", 10, "w");

        System.out.println(jedis.get("name"));
        Thread.sleep(11 * 1000);
        System.out.println(jedis.get("name"));
        AtomicReference<Thread> reference = new AtomicReference<>();
        reference.compareAndSet()
    }
}
