package cn.itlou.exam.util.sms;

import lombok.Data;

import java.util.Date;

/**
 * 短信发送详情DTO
 *
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 13:54
 **/
@Data
public class SmsSendDetailDTO {

    private Date SendDate;
    private int SendStatus;
    private Date ReceiveDate;
    private String ErrCode;
    private String TemplateCode;
    private String Content;
    private String PhoneNum;

}
