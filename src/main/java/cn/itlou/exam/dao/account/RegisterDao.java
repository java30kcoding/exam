package cn.itlou.exam.dao.account;

import cn.itlou.exam.manager.account.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 10:57
 **/
@Mapper
public interface RegisterDao {

    UserInfoDTO queryOne();

    /**
     * 如果不存在则插入
     *
     * @param userInfoDTO
     * @return
     */
    boolean insertIfNotExist(@Param("userInfoDTO") UserInfoDTO userInfoDTO);

}
