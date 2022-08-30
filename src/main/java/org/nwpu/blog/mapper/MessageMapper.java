package org.nwpu.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nwpu.blog.bean.Message;

import java.util.List;

@Mapper
public interface MessageMapper {

    /**
     * 根据留言id查询留言
     * @param messageId 留言id
     * @return 查询到的留言
     */
    public Message getMessageById(@Param("messageId") Integer messageId);

    /**
     * 插入一条新留言
     * @param message 要插入的留言实例
     * @return 操作结果
     */
    public boolean insertMessage(@Param("message")Message message);

    /**
     * 按页查询一级评论
     * @param start 起始下标
     * @param pageSize 一页的大小
     * @return 查询结果
     */
    public List<Message> searchParentMessages(@Param("start") Integer start,@Param("pageSize") Integer pageSize);

    /**
     * 根据父评论id查询子评论
     * @param messages 父评论列表
     * @return 查询结果
     */
    public List<Message> getChildMessagesByPid(@Param("messages") List<Message> messages);

    /**
     * 查询评论总数
     * @param isParent 是否只查询一级评论
     * @return 一级评论总数
     */
    public Integer countParentMessages(@Param("isParent") boolean isParent);

    /**
     * 按页查询一级评论
     * @param start 起始下标
     * @param pageSize 一页的大小
     * @param hasReply true查询全部一级评论，false查询无评论的一级评论
     * @return 查询结果
     */
    public List<Message> searchParentMessagesReply(@Param("start") Integer start,@Param("pageSize") Integer pageSize,@Param("hasReply") boolean hasReply);

    /**
     * 查询一级评论数量
     * @param hasReply true查询全部一级评论，false查询无评论的一级评论
     * @return 一级评论数量
     */
    public Integer countMessagesReply(@Param("hasReply") boolean hasReply);
}
