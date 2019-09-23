package cn.itlou.exam.controller.account;

import cn.itlou.exam.dao.account.RegisterDao;
import cn.itlou.exam.manager.account.RegisterManager;
import cn.itlou.exam.manager.account.dto.ResponseDTO;
import cn.itlou.exam.manager.account.dto.UserInfoDTO;
import cn.itlou.exam.service.account.RegisterService;
import cn.itlou.exam.util.sms.SmsUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 注册
 *
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 10:57
 **/
@Slf4j
@RestController
public class RegisterController {

    @Resource
    RegisterManager registerManager;

    @Resource
    RegisterDao registerDao;

    @PostMapping("/register")
    public String  register(@RequestBody String userInfo){
        //用户信息Json
        JSONObject userInfoJson = JSONObject.parseObject(userInfo);
        if (registerManager.handleRegisterInfo(userInfoJson)) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode(200);
            responseDTO.setMessage("注册成功!");
            return JSONObject.toJSONString(responseDTO);
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode(400);
            responseDTO.setMessage("注册失败!");
            return JSONObject.toJSONString(responseDTO);
        }

//        CommonResponse commonResponse = SmsUtil.sendMessage("13604304443", "SMS_171117464", "十次方", "{\"checkcode\": \"" + 623498 + "\"}");
//        System.out.println(SmsUtil.queryAvailableCode("13604304443"));
    }

}
