package cn.itlou.exam.manager.account;

import cn.itlou.exam.manager.account.dto.UserInfoDTO;
import cn.itlou.exam.service.account.RegisterService;
import cn.itlou.exam.util.sms.SmsUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 14:26
 **/
@Component
public class RegisterManager {

    @Resource
    RegisterService registerService;

    public boolean handleRegisterInfo(JSONObject jsonObject) {
        //1.验证码是否正确
        if (!SmsUtil.queryAvailableCode(jsonObject.getString("userMobile")).equals(jsonObject.getString("userCode"))) {
            return false;
        }
        //2.存库
        return registerService.insertIfNotExist(JSONObject.parseObject(jsonObject.toJSONString(), UserInfoDTO.class));
    }

}
