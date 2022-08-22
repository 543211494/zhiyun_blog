package org.nwpu.blog.result;

import lombok.Data;

/**
 * 分类收藏量统计类
 */
@Data
public class CategoryCollection {

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 被收藏数目
     */
    private Integer collectionNumber;

    public CategoryCollection(String categoryName, Integer collectionNumber) {
        this.categoryName = categoryName;
        this.collectionNumber = collectionNumber;
    }

    public CategoryCollection() {
    }
}
