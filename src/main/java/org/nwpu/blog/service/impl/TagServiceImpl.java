package org.nwpu.blog.service.impl;

import org.nwpu.blog.bean.Tag;
import org.nwpu.blog.mapper.TagMapper;
import org.nwpu.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Integer> addTags(Tag[] tags) {
        List<Integer> ids = null;
        if(tags!=null&&tags.length!=0){
            String[] names = new String[tags.length];
            for(int i = 0;i<names.length;i++){
                names[i] = tags[i].getName();
            }
            try{
                tagMapper.insertTags(tags);
                ids = tagMapper.getTagsIdByName(names);
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
            return ids;
        }else{
            return null;
        }
    }
}
