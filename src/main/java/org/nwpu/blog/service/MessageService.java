package org.nwpu.blog.service;

import org.nwpu.blog.bean.Message;

import java.util.List;

public interface MessageService {

    /**
     * 跟据留言id查询留言
     * @param messageId 留言id
     * @return 查询到的留言
     */
    public Message getMessageById(Integer messageId);

    /**
     * 新增一条留言
     * @param message 要新增的留言实例
     * @return 操作结果
     */
    public boolean addMessage(Message message);

    /**
     * 按页查询留言板
     * @param currentPage 当前页码
     * @param pageSize 一页的大小
     * @param result 存放查询结果的列表
     * @return 总页数
     */
    public Integer getMessageBoard(Integer currentPage, Integer pageSize, List<Message> result);

    /**
     * 查询一级评论
     * @param currentPage 当前页码
     * @param pageSize 一页的大小
     * @param result 存放查询结果的列表
     * @param hasReply true查询全部一级评论，false查询无评论的一级评论
     * @return 总页数
     * @return
     */
    public Integer getMessagesReply(Integer currentPage,Integer pageSize,List<Message> result,boolean hasReply);
}
