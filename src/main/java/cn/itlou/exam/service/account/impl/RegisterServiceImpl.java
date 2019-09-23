package cn.itlou.exam.service.account.impl;

import cn.itlou.exam.dao.account.RegisterDao;
import cn.itlou.exam.manager.account.RegisterManager;
import cn.itlou.exam.manager.account.dto.UserInfoDTO;
import cn.itlou.exam.service.account.RegisterService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 11:01
 **/
@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService {

    @Resource
    RegisterManager registerManager;

    @Resource
    RegisterDao registerDao;

    @Override
    public boolean insertIfNotExist(UserInfoDTO userInfoDTO) {
        return registerDao.insertIfNotExist(userInfoDTO);
    }

}
