package com.markerhub.controller;


import cn.hutool.core.bean.BeanUtil;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.common.lang.Result;
import com.markerhub.entity.Blog;
import com.markerhub.service.BlogService;
import com.markerhub.utils.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2023-07-11
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;
    @GetMapping("/blogs")
    @ApiOperation(value = "主页")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page = new Page(currentPage,5);
        IPage<Blog> pageData = blogService.page(page, new QueryWrapper<Blog>().eq("status",0).orderByDesc("created"));
        return Result.succ(pageData);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "回显")
    public Result detail(@PathVariable(name = "id") Long id){
        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已被删除");
        return Result.succ(blog);
    }


    //Authorization
    @RequiresAuthentication
    @PostMapping ("/edit")
    @ApiOperation(value = "编辑")
    public Result edit(@RequestBody Blog blog){
        Blog b = null;
        if (blog.getId()!=null){
            b = blogService.getById(blog.getId());
            //只能编辑自己的文章
            Assert.isTrue(b.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(),"没有权限编辑");
        }else{
            b = new Blog();
            b.setUserId(ShiroUtil.getProfile().getId());
            b.setCreated(LocalDateTime.now());
            b.setStatus(0);

        }
        BeanUtil.copyProperties(blog,b,"id","userId","created","status");
        blogService.saveOrUpdate(b);
        return Result.succ(b);
    }
    @RequiresAuthentication
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    public Result delete(@PathVariable(value = "id") Long id){
        Blog blog = new Blog();
        blog.setId(id);
        blog.setStatus(1);
        blogService.saveOrUpdate(blog);
        return Result.succ(blog);

    }

}
