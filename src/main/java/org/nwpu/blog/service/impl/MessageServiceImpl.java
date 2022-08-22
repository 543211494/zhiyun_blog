package org.nwpu.blog.service.impl;

import org.nwpu.blog.bean.Message;
import org.nwpu.blog.mapper.MessageMapper;
import org.nwpu.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Message getMessageById(Integer messageId) {
        return messageMapper.getMessageById(messageId);
    }

    @Override
    public boolean addMessage(Message message) {
        try{
            messageMapper.insertMessage(message);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Integer getMessageBoard(Integer currentPage, Integer pageSize, List<Message> result) {
        int start = (currentPage.intValue()-1)*pageSize.intValue();
        int total = messageMapper.countMessages(true).intValue();
        int pageNum = (total/pageSize)+(total%pageSize==0?0:1);
        List<Message> parent = messageMapper.searchParentMessages(start,pageSize);
        if(parent==null||parent.size()==0){
            return pageNum;
        }
        List<Message> child = messageMapper.getChildMessagesByPid(parent);
        if(child!=null&&child.size()!=0){
            for(int i = 0;i<parent.size();i++){
                parent.get(i).setChild(new ArrayList<Message>());
                for(int j = 0;j<child.size();j++){
                    if(child.get(j).getPid().equals(parent.get(i).getId())){
                        parent.get(i).getChild().add(child.get(j));
                    }
                }
            }
        }
        for(int i = 0;i<parent.size();i++){
            result.add(parent.get(i));
        }
        return pageNum;
    }

    @Override
    public Integer getMessagesReply(Integer currentPage, Integer pageSize, List<Message> result, boolean hasReply) {
        int start = (currentPage.intValue()-1)*pageSize.intValue();
        int total = messageMapper.countMessagesReply(hasReply);
        int pageNum = (total/pageSize)+(total%pageSize==0?0:1);
        List<Message> parent = messageMapper.searchParentMessagesReply(start,pageSize,hasReply);
        if(parent==null||parent.size()==0){
            return pageNum;
        }
        List<Message> child = messageMapper.getChildMessagesByPid(parent);
        if(child!=null&&child.size()!=0){
            for(int i = 0;i<parent.size();i++){
                parent.get(i).setChild(new ArrayList<Message>());
                for(int j = 0;j<child.size();j++){
                    if(child.get(j).getPid().equals(parent.get(i).getId())){
                        parent.get(i).getChild().add(child.get(j));
                    }
                }
            }
        }
        for(int i = 0;i<parent.size();i++){
            result.add(parent.get(i));
        }
        return pageNum;
    }
}
