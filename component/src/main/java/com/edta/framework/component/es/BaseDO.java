package com.edta.framework.component.es;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author wangluyao
 * @date 2022/6/19 11:59
 * @description
 */
@Data
public class BaseDO {

    @Field(value = "is_delete", type = FieldType.Date)
    private Long deleted = 0L;

    @Field(value = "create_time", type = FieldType.Date)
    private Long createTime;

    @Field(value = "update_time", type = FieldType.Date)
    private Long updateTime = System.currentTimeMillis();

    public void setDeleted(Boolean deleted) {
        if (deleted) {
            this.deleted = System.currentTimeMillis();
        } else {
            this.deleted = 0L;
        }
    }

    public Boolean isDeleted() {
        return deleted > 0;
    }
}
