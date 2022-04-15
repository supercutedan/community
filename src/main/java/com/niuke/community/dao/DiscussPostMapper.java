package com.niuke.community.dao;

import com.niuke.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    /**
     * 分页查询帖子
     *
     * @param userId 查询ID
     * @param offset 开始索引
     * @param limit 一页多少条记录
     * @return
     */
    List<DiscussPost> selectDiscussPosts(
            @Param("userId") int userId,
            @Param("offset") int offset,
            @Param("limit") int limit);

    /**
     * 查询用户的帖子
     * @Param 注解用于给定参数取别名,防止参数过长
     * 如果只有一个参数，并且在<if></if>里面使用，则必须加别名
     * 如果需要在mysql动态的拼一个条件，但是这个方法里有且只有一个条件，就需要加param
     * @Param 注解用于给参数取别名，当这个方法中只有一个参数时并且这个参数在mapper.xml文件中被使用在<if>标签中时，就必须要使用@Param注解来给这个参数取个别名
     * 上面3行是一个意思
     * @param userId
     * @return
     */
    int selectDiscussPostRows(@Param("userId") int userId);//查询一共有多少个帖子
}
