package com.edta.framework.component.fs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author wangluyao
 * @date 2022/7/9 14:30
 * @description
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {

    private String md5sum;
    private String filename;
    private Long size;
    private String path;
    private String type;
    private String suffixName;
}
