package com.edta.framework.component.excel;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author wangluyao
 * @date 2022/10/16 18:39
 * @description
 */
public class ReadExcel {

    @Test
    void readExcel1() {
        File file = new File("C:\\Users\\wly32\\Documents\\Work\\20220601_XX7HH\\xx7hh\\01trunk\\services\\hh\\trunk\\hh-2.0\\hh-admin\\src\\main\\resources\\template\\TUPLE5.xlsx");
        parseExcel(FileUtil.getInputStream(file));
    }

    public List<Map<String, Object>> parseExcel(InputStream inputStream) {
        EasyExcel.read(inputStream, new ReadListener() {
            @Override
            public void onException(Exception e, AnalysisContext analysisContext) throws Exception {
                System.out.println("onException");
            }

            @Override
            public void invokeHead(Map map, AnalysisContext analysisContext) {
                System.out.println("invokeHead");
            }

            @Override
            public void invoke(Object o, AnalysisContext analysisContext) {
                System.out.println("invoke");
            }

            @Override
            public void extra(CellExtra cellExtra, AnalysisContext analysisContext) {
                System.out.println("extra");
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println("doAfterAllAnalysed");
            }

            @Override
            public boolean hasNext(AnalysisContext analysisContext) {
                return true;
            }
        }).sheet().doRead();
        return null;
    }
}
