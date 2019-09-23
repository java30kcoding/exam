package cn.itlou.exam.service.account;

import org.apache.ibatis.annotations.Param;

/**
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 10:57
 **/
public interface LoginService {

    int queryCountByPassword(String userName, String password);

    int queryCountByMobile(String mobile);

}
