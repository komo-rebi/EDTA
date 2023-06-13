package com.edta.framework.component.ck;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.edta.framework.component.mybatisplus.BaseDO;
import lombok.Data;

/**
 * @author wangluyao
 * @date 2022/10/9 19:31
 * @description
 */
@Data
@TableName("hh_role")
public class RoleDO extends BaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "role_id")
    private String roleId;

    @TableField(value = "description")
    private String description;
}
