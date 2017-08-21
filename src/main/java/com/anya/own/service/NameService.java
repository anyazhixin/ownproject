package com.anya.own.service;

import com.anya.own.dto.Request;
import com.anya.own.dto.Result;
import com.anya.own.dto.test.GetUserRequest;
import com.anya.own.dto.test.UserDTO;

/**
 * @author wuwenliang
 * @date 2017-08-04.
 */
public interface NameService {

    Result<UserDTO> getUser(Request<GetUserRequest> request);

}
