package cn.itlou.exam.util.random;

import java.util.Random;

/**
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 17:04
 **/
public class RandomUtil {

    private RandomUtil(){}

    /**
     * 生成6位验证码
     *
     * @return
     */
    public static String randomCode(){
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10);
            code = code + r;
        }
        return code;
    }

}
