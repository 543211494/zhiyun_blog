package org.nwpu.blog.result;

import lombok.Data;

/**
 * 分类数据统计类
 * @author lzy
 * @date 2022/8/22
 */
@Data
public class CategoryData {

    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 分类出现次数
     */
    private Integer count = 0;

    public CategoryData(Integer categoryId, String categoryName, Integer count) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.count = count;
    }

    public CategoryData() {
    }
}
