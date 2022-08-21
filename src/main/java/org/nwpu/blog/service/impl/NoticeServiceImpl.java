package org.nwpu.blog.service.impl;

import org.nwpu.blog.bean.Notice;
import org.nwpu.blog.mapper.NoticeMapper;
import org.nwpu.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public boolean insertNotice(Notice notice) {
        try{
            noticeMapper.insertNotice(notice);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateNotice(Notice notice) {
        try{
            noticeMapper.updateNoticeById(notice);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteNoticeById(Integer noticeId) {
        try{
            noticeMapper.deleteNotcieById(noticeId);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Integer getNotices(Integer currentPage, Integer pageSize, List<Notice> result, String content) {
        int start = (currentPage.intValue()-1)*pageSize.intValue();
        List<Notice> temp = noticeMapper.getNotices(start,pageSize,content);
        if(temp!=null){
            for(int i = 0;i<temp.size();i++){
                result.add(temp.get(i));
            }
        }
        int total = noticeMapper.countNotice(content);
        int pageNum = (total/pageSize)+(total%pageSize==0?0:1);
        return pageNum;
    }
}
