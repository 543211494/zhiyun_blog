package org.nwpu.blog.service.impl;

import org.nwpu.blog.bean.Category;
import org.nwpu.blog.mapper.CategoryMapper;
import org.nwpu.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Integer addCategory(Category category) {
        try{
            categoryMapper.insertCategory(category);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        if(category.getId()==null){
            return categoryMapper.getCategoryByName(category.getName()).getId();
        }
        return category.getId();
    }
}
