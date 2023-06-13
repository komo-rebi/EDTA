package com.edta.framework.component.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author wangluyao
 * @date 2022/7/9 10:39
 * @description
 */
@Data
@Document(indexName = "user", type = "user")
public class UserDO {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(name = "user_name", type = FieldType.Keyword)
    private String username;

    @Field(name = "passWord", type = FieldType.Keyword)
    private String password;
}
