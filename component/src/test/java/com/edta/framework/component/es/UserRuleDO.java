package com.edta.framework.component.es;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author wangluyao
 * @date 2022/6/13 16:47
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Document(indexName = "hh_user_rule_v2_dev")
public class UserRuleDO extends BaseDO {

    @Id
    @Field(name = "user_rule_id", type = FieldType.Keyword)
    private String userRuleId;

    @Field(name = "user_id", type = FieldType.Integer)
    private Integer userId;

    @Field(name = "set_id", type = FieldType.Integer)
    private Integer setId;

    @Field(name = "operator_id", type = FieldType.Integer)
    private Integer operatorId;

    @Field(name = "biz_id", type = FieldType.Keyword)
    private String bizId;

    @Field(name = "start_time", type = FieldType.Date)
    private Long startTime;

    @Field(name = "end_time", type = FieldType.Date)
    private Long endTime;

    @Field(name = "priority", type = FieldType.Integer)
    private Integer priority;

    @Field(name = "direction", type = FieldType.Keyword)
    private String direction;

    @Field(name = "sampling_scale", type = FieldType.Double)
    private Double samplingScale;

    @Field(name = "back_method", type = FieldType.Integer)
    private Integer backMethod;

    @Field(name = "package_type", type = FieldType.Integer)
    private Integer packageType;

    @Field(name = "content", type = FieldType.Keyword)
    private String content;

    @Field(name = "version", type = FieldType.Integer)
    private Integer version = 2;

    @Field(name = "system_rule_id", type = FieldType.Keyword)
    private Integer systemRuleId;

    @Field(name = "log_id", type = FieldType.Keyword)
    private String logId;

    @Field(name = "protocol", type = FieldType.Keyword)
    private Integer protocol;

    @Field(name = "md5", type = FieldType.Keyword)
    private String md5;
}
