package com.edta.framework.component;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author wangluyao
 * @date 2022/10/9 19:28
 * @description
 */
//@Component
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public void getOne() {
        List<RoleDO> roleDO = roleMapper.selectList(Wrappers.lambdaQuery());
        System.out.println(roleDO.get(0));
    }
}
