package com.edta.framework.component.dto;

import com.edta.framework.component.dto.response.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wly
 * @date 2020/12/22 22:36:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class PageResponse<T> extends Response {

    private PageResult<T> result;

    PageResponse(PageResult<T> result) {
        this.result = result;
    }

    public static <T> PageResponse<T> build(ResponseCode responseCode, PageResult<T> result) {
        return new PageResponse<>(result).setResponseCode(responseCode);
    }

    public static <T> PageResponse<T> buildSuccess(PageResult<T> result) {
        return new PageResponse<>(result);
    }

    public static <T> PageResponse<T> build(ResponseCode responseCode) {
        return build(responseCode, PageResult.of());
    }

    public static <T> PageResponse<T> buildSuccess() {
        return buildSuccess(PageResult.of());
    }
}
