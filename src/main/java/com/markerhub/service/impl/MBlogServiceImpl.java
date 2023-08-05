package com.markerhub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.entity.MBlog;
import com.markerhub.service.MBlogService;
import com.markerhub.mapper.MBlogMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【m_blog】的数据库操作Service实现
* @createDate 2023-07-11 23:14:12
*/
@Service
public class MBlogServiceImpl extends ServiceImpl<MBlogMapper, MBlog>
    implements MBlogService{

}




