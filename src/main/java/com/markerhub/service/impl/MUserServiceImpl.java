package com.markerhub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.entity.MUser;
import com.markerhub.service.MUserService;
import com.markerhub.mapper.MUserMapper;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【m_user】的数据库操作Service实现
* @createDate 2023-07-11 23:13:27
*/
@Service
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser>
    implements MUserService{

}




