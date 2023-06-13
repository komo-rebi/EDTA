package com.edta.framework.component.event;

import com.edta.framework.component.domain.Event;
import com.edta.framework.component.domain.EventDesc;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wangluyao
 * @date 2022/10/20 19:24
 * @description
 */
@AllArgsConstructor
@Getter
@EventDesc(channel = "com.edta.framework.component.event", prefix = "user", dataSource = DataSource.redis)
public class CreateUserEvent extends Event {

    private Integer userId;
}
