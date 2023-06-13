package com.edta.framework.component.catchlog;

import org.springframework.stereotype.Service;

/**
 * @author wangluyao
 * @date 2022/7/4 13:50
 * @description
 */
@Service
public class UserService {

    @CatchAndLog
    public int addUser(String username, String password) {
        return add();
    }

    @CatchAndLog
    private int add() {
        System.out.println();
        return 0;
    }

}
