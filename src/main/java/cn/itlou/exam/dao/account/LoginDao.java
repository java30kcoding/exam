package cn.itlou.exam.dao.account;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 登录
 *
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 10:57
 **/
@Mapper
public interface LoginDao {

    /**
     *
     * @param userName
     * @param password
     * @return
     */
    int queryCountByPassword(@Param("userName") String userName, @Param("password")String password);

    int queryCountByMobile(String mobile);

}
