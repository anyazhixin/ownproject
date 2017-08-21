package com.anya.own;

import com.anya.own.constant.NormalConstant;
import com.anya.own.dto.Request;
import com.anya.own.dto.Result;
import com.anya.own.dto.test.GetUserRequest;
import com.anya.own.dto.test.UserDTO;
import com.anya.own.service.NameService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.net.URL;

/**
 * @author wuwenliang
 * @date 2017-08-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class BaseTest {
    
    static {

//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        /*try {
            configurator.doConfigure("");
        } catch (JoranException e) {
            throw new RuntimeException("读取logback.xml配置出错", e);
        } finally {
            StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
        }*/
        String configPath = getConfigPath();
    }
    private static String getConfigPath() {
        String className = BaseTest.class.getName();
        String classNamePath = className.replace(".", "/") + ".class";
        URL is = BaseTest.class.getClassLoader().getResource(classNamePath);
        String path = is.getFile();
        path = StringUtils.replace(path, "%20", " ");
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            path = StringUtils.removeStart(path, "/");
        }
        path = StringUtils.remove(path, "test-classes/" + classNamePath) + "classes";

        System.out.println("配置文件路径：" + path);
        return path;
    }
    
    @Autowired
    private NameService nameService;
    
    @Test
    public void getNameTest() {
        Request<GetUserRequest> request = new Request<>();
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setName("李四");
        request.setData(getUserRequest);
        Result<UserDTO> user = nameService.getUser(request);
    }

    @Test
    public void myTest() {
        NormalConstant normalConstant = new NormalConstant();
        Class aClass = normalConstant.getClass();
        Field age = null;
        try {
            Field[] declaredFields = aClass.getDeclaredFields();
            age = aClass.getDeclaredField("age");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (null == age) {
            return;
        }
        age.setAccessible(true);
        try {
            age.set(normalConstant, 20);
            System.out.println(age.get(normalConstant));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(normalConstant.age);
    }
}
