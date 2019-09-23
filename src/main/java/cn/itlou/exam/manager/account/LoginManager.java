package cn.itlou.exam.manager.account;

import cn.itlou.exam.manager.account.dto.UserInfoDTO;
import cn.itlou.exam.service.account.LoginService;
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
public class LoginManager {

    @Resource
    LoginService loginService;

    public int handleLoginByPassword(JSONObject jsonObject) {
        return loginService.queryCountByPassword(jsonObject.getString("userName"), jsonObject.getString("userPassword"));
    }

    public boolean handleLoginByMobile(String mobile, String mobileCode) {
        if (mobileCode.equals(SmsUtil.queryAvailableCode(mobile)) && loginService.queryCountByMobile(mobile) == 1) {
            return true;
        } else {
            return false;
        }
    }

}
