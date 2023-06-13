package com.edta.framework.component.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangluyao
 * @date 2022/10/8 20:10
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdResult<T> extends Result {

    private T id;
    private String name;
}
