package com.edta.framework.component.mybatisplus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * @author wly
 * @date 2021/07/14 11:54:16
 */
@Data
public class BaseDO {

    @TableField(value = "is_delete", fill = FieldFill.INSERT)
    @TableLogic
    private Long deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Long createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

}
