package com.anya.own.convert;

import com.anya.own.dao.bean.test.UserDO;
import com.anya.own.dto.test.UserDTO;

import java.lang.reflect.InvocationTargetException;

/**
 * @author wuwenliang
 * @date 2017-08-19.
 */
public class TestConvert {
    public static UserDTO convertFrom(UserDO userDO) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        UserDTO userDTO = new UserDTO();
        long startTime = System.currentTimeMillis();
//        BeanUtils.copyProperties(userDTO, userDO);
//        PropertyUtils.copyProperties(userDTO, userDO);
        org.springframework.beans.BeanUtils.copyProperties(userDO, userDTO);
        long secondTime = System.currentTimeMillis();
//        long thirdTime = System.currentTimeMillis();
//        long forthTime = System.currentTimeMillis();
        System.out.println(secondTime - startTime);
//        System.out.println(thirdTime - secondTime);
//        System.out.println(forthTime - thirdTime);
        return userDTO;
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        UserDO userDO = new UserDO();
//        userDO.setId(1);
//        userDO.setName("???");
//        userDO.setAddress("");
//        userDO.setSex(null);
//        userDO.setAge(null);
//        UserDTO userDTO = new UserDTO();
//        long startTime = System.currentTimeMillis();
//        PropertyUtils.copyProperties(userDTO, userDO);
//        BeanUtils.copyProperties(userDTO, userDO);
//        org.springframework.beans.BeanUtils.copyProperties(userDO, userDTO);
//        long secondTime = System.currentTimeMillis();
//        System.out.println(secondTime - startTime);
//        System.out.println(userDTO.toString());

        String name = "的防守打法";
        byte[] bytes = name.getBytes();
    }
}
