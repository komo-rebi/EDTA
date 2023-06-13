package com.edta.framework.component.curd;

import com.edta.framework.component.dto.PageResult;
import com.edta.framework.component.util.XmlUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author wangluyao
 * @date 2022/7/28 17:28
 * @description
 */
public class Retrieve<Q extends JsonQuery, R, DO> {

    private final String name;
    private final QFunction<Q, DO> queryFunction;
    private final ConvertFunction<DO, R> convertFunction;

    private List<CurdAop.Before<Q>> beforeChains;
    private List<CurdAop.AfterReturning<PageResult<R>>> afterReturningChains;
    private List<CurdAop.AfterThrowing<Q>> afterThrowingChains;
    private List<CurdAop.After<Q>> afterChains;

    public Retrieve(String xml) {
        this.name = XmlUtil.xmlToBean(xml, "name", String.class);
        String input = XmlUtil.xmlToBean(xml, "retrieve.input", String.class);
        String output = XmlUtil.xmlToBean(xml, "retrieve.output", String.class);
        String queryFunctionClassName = XmlUtil.xmlToBean(xml, "retrieve.queryFunction", String.class);
        String convertFunctionClassName = XmlUtil.xmlToBean(xml, "retrieve.output", String.class);
        String before = XmlUtil.xmlToBean(xml, "retrieve.before", String.class);
        String after = XmlUtil.xmlToBean(xml, "retrieve.after", String.class);
        String around = XmlUtil.xmlToBean(xml, "retrieve.around", String.class);
        String afterThrowing = XmlUtil.xmlToBean(xml, "retrieve.afterThrowing", String.class);
        String afterReturning = XmlUtil.xmlToBean(xml, "retrieve.afterReturning", String.class);

        this.queryFunction = newClass(queryFunctionClassName, new QFunction.DefaultQFunction());
        this.convertFunction = newClass(convertFunctionClassName, new ConvertFunction.DefaultConvertFunction<>());
        beforeChains.add(newClass(before, new CurdAop.DefaultBefore<>()));
        afterChains.add(newClass(after, new CurdAop.DefaultAfter<>()));
        afterThrowingChains.add(newClass(afterThrowing, new CurdAop.DefaultAfterThrowing<>()));
        afterReturningChains.add(newClass(afterReturning, new CurdAop.DefaultAfterReturning<>()));
    }

    @SneakyThrows
    private <T> T newClass(String className, T defaultClass) {
        if (StringUtils.isBlank(className)) {
            return defaultClass;
        }
        return (T) Class.forName(className).newInstance();
    }

    public String getName() {
        return name;
    }

    public void before(Q query) {
        beforeChains.forEach(before -> before.exec(query));
    }

    public void afterReturning(PageResult<R> pageResult) {
        afterReturningChains.forEach(afterReturning -> {
            afterReturning.exec(pageResult);
        });
    }

    public void afterThrowing(Q query, Throwable e) {
        afterThrowingChains.forEach(afterThrowing -> afterThrowing.exec(query, e));
    }

    public void after(Q query) {
        afterChains.forEach(after -> after.exec(query));
    }

    public PageResult<DO> exec(Q query) {
        return queryFunction.exec(query);
    }

    public PageResult<R> convert(PageResult<DO> dataObjectResult) {
        return dataObjectResult.convert2PageResult(this.convertFunction);
    }

}
