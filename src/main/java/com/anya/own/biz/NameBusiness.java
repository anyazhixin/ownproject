package com.anya.own.biz;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.anya.own.common.dao.DataSource;
import com.anya.own.common.dao.DataSourceHolder;
import com.anya.own.convert.TestConvert;
import com.anya.own.dao.bean.test.UserDO;
import com.anya.own.dao.bean.test.UserDOExample;
import com.anya.own.dao.mapper.test.UserDOMapper;
import com.anya.own.dto.test.GetUserRequest;
import com.anya.own.dto.test.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author wuwenliang
 * @date 2017-08-18.
 */
@Component
public class NameBusiness {

    @Autowired
    private UserDOMapper userDOMapper;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UserDTO getUser(GetUserRequest data) {
        UserDOExample example = new UserDOExample();
        example.createCriteria().andNameEqualTo(data.getName());
        List<UserDO> userDOS = userDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userDOS)) {
            return null;
        }
        UserDO userDO = userDOS.get(0);
        UserDTO user = null;
        try {
            user = TestConvert.convertFrom(userDO);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return user;
    }
}
