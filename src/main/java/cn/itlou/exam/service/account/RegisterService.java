package cn.itlou.exam.service.account;

import cn.itlou.exam.manager.account.dto.UserInfoDTO;
import com.alibaba.fastjson.JSONObject;

/**
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 10:57
 **/
public interface RegisterService {



    /**
     * 如果没有则插入
     *
     * @param userInfoDTO
     * @return
     */
    boolean insertIfNotExist(UserInfoDTO userInfoDTO);

}