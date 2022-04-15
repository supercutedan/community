package com.niuke.community.controller;

import com.niuke.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.*;
//import java.util.HashMap;

@Controller
@RequestMapping("/alpha")//这个是网页的路径
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        //前两行是请求行，也就是第一行的数据
        //下面几行是消息头的数据
        Enumeration<String> enumeration= request.getHeaderNames();//得到请求行所有的key,用了一个迭代器进行接收
        while(enumeration.hasMoreElements()){
            String name=enumeration.nextElement();
            String value=request.getHeader(name);
            System.out.println(name+":"+value);
        }
        System.out.println(request.getParameter("code"));//请求体包含的业务数据

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");//设置返回类型,网页类型的文本
        try(
                PrintWriter writer=response.getWriter();//输出流
                )
        {
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //为什么没有返回值是因为，使用response对象可以向浏览器输出任何数据，就不用依赖返回值了

    //GET请求
    //在get请求中有两种传参的方式一种是问号拼，另一种是拼路径
    //浏览器向服务器传参有两种方式，一是在通过get请求，在路径后加问号携带参数，如/xxx?id=1。
    // 另一种是通过post请求，在request请求体中携带表单中的参数，这种参数在路径上是看不到的。这两种方式所传的参数，在服务端都可以通过request.getParameter(参数名)这样的方式来获取。
    // 而@RequestParam注解，就相当于是request.getParameter()，是从request对象中获取参数的。有时，我们也愿意利用请求路径本身来传参，即将参数拼到路径里，如/xxx/1，这里的1就是参数，那么在解析路径的时候，也是能获取到这个参数的。
    // 而@PathVarible就是解析路径，从中获得对应级次的参数

    //  /students?current=1&limit=20
    @RequestMapping(path="/students",method = RequestMethod.GET)  //指定了方法和路径
    @ResponseBody
    public String getStudents(
            @RequestParam(name="current",required = false,defaultValue = "1") int current,
            @RequestParam(name="limit",required = false,defaultValue = "10") int limit
    ){
        //有时初始页面会没有current=1这个路径，所以就要允许没有这个，如果没有设置默认值为1
        return "some students";
    }

    // /student/123
    @RequestMapping(path="/student/{id}",method=RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){ // 而@PathVarible就是解析路径，从中获得对应级次的参数
        return "a student";
    }

    //POST请求
    @RequestMapping(path="/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据
    //方法一,主动实例化model
    @RequestMapping(path="/teacher",method=RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age",30);//模板里有几个属性就加几个object
        mav.setViewName("/demo/view");//模板路径
        return mav;
    }
    //方法二，自动实例化model，更简洁，大多采用这一种
    @RequestMapping(path="/school",method=RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","北京大学");
        model.addAttribute("age",80);
        return "/demo/view";
    }


    //响应JSON数据（异步请求）（异步就相当于，注册时输入用户名，这时网页没有刷新，但是已经对服务器进行了一次访问查询用户名是否和已有用户重复
    //JAVA对象->JSON字符串->JS对象   （JSON字符串常用于这种跨平台语言的转换
    @RequestMapping(path="/emp",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp=new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        return emp;
    }
}
