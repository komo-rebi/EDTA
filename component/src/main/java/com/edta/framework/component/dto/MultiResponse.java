package com.edta.framework.component.dto;

import com.edta.framework.component.dto.response.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;
import java.util.Collections;

/**
 * @author wly
 * @date 2020/12/22 22:34:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class MultiResponse<T> extends Response {

    private Collection<T> result;

    MultiResponse(Collection<T> result) {
        this.result = result;
    }

    public static <T> MultiResponse<T> build(ResponseCode responseCode, Collection<T> result) {
        return new MultiResponse<>(result).setResponseCode(responseCode);
    }

    public static <T> MultiResponse<T> buildSuccess(Collection<T> result) {
        return new MultiResponse<>(result);
    }

    public static <T> MultiResponse<T> build(ResponseCode responseCode) {
        return build(responseCode, Collections.emptyList());
    }

    public static <T> MultiResponse<T> buildSuccess() {
        return buildSuccess(Collections.emptyList());
    }
}
