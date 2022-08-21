package org.nwpu.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.blog.bean.Notice;

import java.util.List;

@Mapper
public interface NoticeMapper {

    /**
     * 插入一条新公告
     * @param notice 要插入的公告实例
     * @return 操作结果
     */
    public boolean insertNotice(@Param("notice")Notice notice);

    /**
     * 根据id修改公告信息
     * @param notice 要修改的公告实例
     * @return 操作结果
     */
    public boolean updateNoticeById(@Param("notice")Notice notice);

    /**
     * 根据公告id删除公告
     * @param noticeId 公告id
     * @return 操作结果
     */
    public boolean deleteNotcieById(@Param("noticeId")Integer noticeId);

    /**
     * 按页查询一组公告
     * @param start 起始下标
     * @param pageSize 一页的大小
     * @param content 公告内容，有则模糊查询，无则查询全部
     * @return 查询到的公告列表
     */
    public List<Notice> getNotices(@Param("start")Integer start,@Param("pageSize")Integer pageSize,@Param("content")String content);

    /**
     * 查询当前数据库中有多少条公告
     * @return 公告数目
     */
    public Integer countNotice(@Param("content") String content);
}
