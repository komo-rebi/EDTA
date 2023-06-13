package com.edta.framework.component.dto;

import com.edta.framework.component.dto.response.IResponseCodeFactory;
import com.edta.framework.component.dto.response.ResponseCode;
import com.edta.framework.component.util.ApplicationContextHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wly
 * @date 2020/12/22 22:19:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public abstract class Response extends DTO {

    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

    Response() {
        IResponseCodeFactory responseCodeFactory = ApplicationContextHelper.getBean(IResponseCodeFactory.class);
        this.code = responseCodeFactory.getSuccessResponseCode().getCode();
        this.message = responseCodeFactory.getSuccessResponseCode().getMessage();
    }

    public boolean success() {
        IResponseCodeFactory responseCodeFactory = ApplicationContextHelper.getBean(IResponseCodeFactory.class);
        return this.code.equals(responseCodeFactory.getSuccessResponseCode().getCode());
    }

    protected <T extends Response> T setResponseCode(ResponseCode responseCode) {
        this.setCode(responseCode.getCode());
        this.setMessage(responseCode.getMessage());
        return (T) this;
    }
}
