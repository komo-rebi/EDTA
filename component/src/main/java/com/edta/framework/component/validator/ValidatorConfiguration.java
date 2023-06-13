package com.edta.framework.component.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author zlf
 */
@Configuration
public class ValidatorConfiguration {

    @Bean
    @ConditionalOnMissingBean(Validator.class)
    public Validator validtor() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    @ConditionalOnMissingBean(ParameterCheckAspect.class)
    public ParameterCheckAspect parameterCheckAspect(@Autowired Validator validator) {
        return new ParameterCheckAspect(validator);
    }
}
