package com.edta.framework.component.dto;

import com.edta.framework.component.curd.ConvertFunction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.var;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author wly
 * @date 2021/01/07 17:14:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageResult<T> extends Result {

    private static final long serialVersionUID = 1L;

    private Collection<T> rows;
    private Long total;

    private PageResult(Long total, Collection<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public static <T> PageResult<T> of(long total, Collection<T> rows) {
        if (CollectionUtils.isEmpty(rows)) {
            rows = new ArrayList<>();
        }
        return new PageResult<>(total, rows);
    }

    public static <T> PageResult<T> of() {
        return PageResult.of(0, Collections.emptyList());
    }

    public <D> PageResult<D> convert2PageResult(ConvertFunction<T, D> convertFunction) {
        var list = this.getRows().stream().map(convertFunction::exec).collect(Collectors.toList());
        return PageResult.of(this.total, list);
    }
}
