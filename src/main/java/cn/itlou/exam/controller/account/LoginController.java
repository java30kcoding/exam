package cn.itlou.exam.controller.account;

import cn.itlou.exam.manager.account.LoginManager;
import cn.itlou.exam.manager.account.dto.ResponseDTO;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 10:57
 **/
@Slf4j
@RestController
public class LoginController {

    @Resource
    LoginManager loginManager;

    @PostMapping("/loginByPassword")
    public String loginByPassword(@RequestBody String userInfo){
        JSONObject userInfoJson = JSONObject.parseObject(userInfo);
        int i = loginManager.handleLoginByPassword(userInfoJson);
        if (i == 1) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode(200);
            responseDTO.setMessage("登录成功!");
            return JSONObject.toJSONString(responseDTO);
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode(400);
            responseDTO.setMessage("登录失败!");
            return JSONObject.toJSONString(responseDTO);
        }
    }

    @PostMapping("/loginByMobile")
    public String loginByMobile(@RequestBody String userInfo){
        JSONObject userInfoJson = JSONObject.parseObject(userInfo);
        if (loginManager.handleLoginByMobile(userInfoJson.getString("userMobile"), userInfoJson.getString("userCode"))) {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode(200);
            responseDTO.setMessage("登录成功!");
            return JSONObject.toJSONString(responseDTO);
        } else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setCode(400);
            responseDTO.setMessage("登录失败!");
            return JSONObject.toJSONString(responseDTO);
        }
    }

}
