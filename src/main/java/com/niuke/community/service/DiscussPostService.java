package com.niuke.community.service;

import com.niuke.community.dao.DiscussPostMapper;
import com.niuke.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    /**
     * 分页查询帖子
     * @param userId 当前用户, 若为0则表示所有用户
     * @param offset 当前页的起始行
     * @param limit 每页多少条
     */
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    /**
     * 查询帖子
     * @param userId 当前用户, 若为0则表示所有用户
     */
    public int findDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
