package cn.itlou.exam.controller.common;

import cn.itlou.exam.manager.account.dto.ResponseDTO;
import cn.itlou.exam.util.random.RandomUtil;
import cn.itlou.exam.util.sms.SmsUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 16:24
 **/
@RestController
@Slf4j
public class SmsController {

    @GetMapping("/sendSms")
    public String getCode(String mobile){

        CommonResponse commonResponse = SmsUtil.sendMessage(mobile, "SMS_171117464", "十次方", "{\"checkcode\": \"" + RandomUtil.randomCode() + "\"}");
        if (commonResponse.getHttpStatus() == 200) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode(200);
            responseDTO.setMessage("短信已发送!");
            return JSONObject.toJSONString(responseDTO);
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode(400);
            responseDTO.setMessage("短信发送失败!请稍后再试...");
            return JSONObject.toJSONString(responseDTO);
        }

    }

}
