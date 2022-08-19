package org.nwpu.blog.service;

import org.nwpu.blog.bean.ArticleCollection;

public interface CollectionService {

    /**
     * 添加一个收藏
     * @param collection 要添加的收藏实例
     * @return 操作结果
     */
    public boolean addCollection(ArticleCollection collection);
}
