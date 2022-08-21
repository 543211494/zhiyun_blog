package org.nwpu.blog.service;

import org.nwpu.blog.bean.Notice;

import java.util.List;

public interface NoticeService {

    /**
     * 新增一条公告
     * @param notice 要新增的公告实例
     * @return 操作结果
     */
    public boolean insertNotice(Notice notice);

    /**
     * 修改公告
     * @param notice 新公告实例
     * @return 操作结果
     */
    public boolean updateNotice(Notice notice);

    /**
     * 根据公告id删除公告(软删除)
     * @param noticeId 公告id
     * @return 操作结果
     */
    public boolean deleteNoticeById(Integer noticeId);

    /**
     * 查询公告
     * @param currentPage 当前页
     * @param pageSize 一页的大小
     * @param result 查询结果
     * @param content 公告内容，有则模糊查询，无则查询全部
     * @return 查询到的公告内容
     */
    public Integer getNotices(Integer currentPage,Integer pageSize,List<Notice> result,String content);
}
