package org.nwpu.blog.service.impl;

import org.nwpu.blog.bean.ArticleCollection;
import org.nwpu.blog.mapper.CollectionMapper;
import org.nwpu.blog.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public boolean addCollection(ArticleCollection collection) {
        try{
            collectionMapper.insertCollection(collection);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
