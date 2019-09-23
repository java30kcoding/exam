package cn.itlou.exam.util.sms;

import cn.itlou.exam.util.file.PropertiesUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 阿里云短信发送模块工具
 *
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 11:16
 **/
@Slf4j
public class SmsUtil {

    private static final String domain = "dysmsapi.aliyuncs.com";
    private static final String version = "2017-05-25";
    private static final String regionId = "cn-hangzhou";
    private static DefaultProfile profile;
    private static String overtime;

    static {
        Properties properties = PropertiesUtil.getProperties("cloud.properties");
        String accessKey = properties.getProperty("aliyun.sms.accesskey");
        String accessSecret = properties.getProperty("aliyun.sms.accesssecret");
        Integer overtime = Integer.valueOf(properties.getProperty("aliyun.sms.overtime"));
        profile = DefaultProfile.getProfile(regionId, accessKey, accessSecret);
    }

    public static CommonResponse sendMessage(String mobile, String templateCode, String signName, String param){

        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateParam", param);
        CommonResponse response = null;
        try {
            response = client.getCommonResponse(request);
            log.info("调用短信发送接口，返回消息: {}", response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (response.getHttpStatus() == 200) {
            log.info("{} 的短信发送成功", mobile);
        }
        return response;
    }

    public static String queryAvailableCode(String mobile){
        JSONObject resJson = JSONObject.parseObject(queryMessage(mobile).getData());
        //获取详细信息
        JSONArray details = resJson.getJSONObject("SmsSendDetailDTOs").getJSONArray("SmsSendDetailDTO");
        return getCode(details.getObject(0, SmsSendDetailDTO.class).getContent());
    }

    public static CommonResponse queryMessage(String mobile){
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(domain);
        request.setVersion(version);
        request.setAction("QuerySendDetails");
        request.putQueryParameter("RegionId", regionId);
        request.putQueryParameter("PhoneNumber", "13604304443");
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.putQueryParameter("SendDate", ft.format(new Date()));
        request.putQueryParameter("PageSize", "10");
        request.putQueryParameter("CurrentPage", "1");
        CommonResponse response = null;
        try {
            response = client.getCommonResponse(request);
            log.info("调用短信查询接口，返回消息: {}", response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (response.getHttpStatus() == 200) {
            log.info("{} 的短信查询成功", mobile);
        }
        return response;
    }

    private static String getCode(String content){
        int start = content.indexOf("：") + 1;
        return content.substring(start, start + 6);
    }

}
