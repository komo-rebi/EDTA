package com.edta.framework.component.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 分页查询参数
 *
 * @author wly
 * @date 2020/12/22 22:36:34
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public abstract class PageQuery extends Query {

    public static final String ASC = "asc";
    public static final String DESC = "desc";
    private static final long serialVersionUID = 1;
    private static final int DEFAULT_PAGE_SIZE = Integer.MAX_VALUE;
    private Integer page = 1;
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    private OrderBy[] orderBy = new OrderBy[]{};

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class OrderBy extends DTO {
        private static final long serialVersionUID = 1L;

        private String name;
        private String by;

        public boolean isAsc() {
            return ASC.equals(by);
        }
    }
}
