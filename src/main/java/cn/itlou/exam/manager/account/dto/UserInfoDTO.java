package cn.itlou.exam.manager.account.dto;

import lombok.Data;

/**
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 14:29
 **/
@Data
public class UserInfoDTO {

    private String userName;
    private String userPassword;
    private String userEmail;
    private String userMobile;

}
