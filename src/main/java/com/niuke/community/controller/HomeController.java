package com.niuke.community.controller;

import com.niuke.community.entity.DiscussPost;
import com.niuke.community.entity.Page;
import com.niuke.community.entity.User;
import com.niuke.community.service.DiscussPostService;
import com.niuke.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//Controller的访问路径是可以省略的，就不用加requestmapping
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path="/index",method = RequestMethod.GET)    //响应的是网页，就不加responsebody了//通过model携带数据给模板
    public String getIndexPage(Model model, Page page){
        /**
         方法调用前, SpringMVC会自动实例化Model和Page,并将Page注入Model.
         所以,在thymeleaf中可以直接访问Page对象中的数据.
         */
        page.setRows(discussPostService.findDiscussPostRows(0));
        // 设置页面路径
        page.setPath("/index");

        //查询10条数据：userid == 0 表示 查询条件userid不起作用，即查询所有记录
        List<DiscussPost> list=discussPostService.findDiscussPosts(0,page.getOffset(),page.getLimit());
        //用Map把 post-帖子和user-帖子对应的用户 装到容器里
        List<Map<String,Object>> discussPosts=new ArrayList<>();
        if(list!=null){
            for(DiscussPost post:list){
                Map<String,Object> map=new HashMap<>();
                map.put("post",post);
                User user=userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);//把东西装进model里
        return "/index";//返回的是模板的路径
    }
}
