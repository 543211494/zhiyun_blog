package org.nwpu.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.blog.bean.Tag;

import java.util.List;

@Mapper
public interface TagMapper {

    /**
     * 批量无重复插入一组标签
     * @param tags
     * @return
     */
    public boolean insertTags(@Param("tags") Tag[] tags);

    /**
     * 根据一组tag名称获取其id
     * @param tagNames
     * @return
     */
    public List<Integer> getTagsIdByName(@Param("names") String[] tagNames);
}
