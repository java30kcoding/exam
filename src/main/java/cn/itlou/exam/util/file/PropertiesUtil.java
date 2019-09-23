package cn.itlou.exam.util.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件工具类
 *
 * @author yuanyl
 * @version V1.0
 * @since 2019/9/23 13:01
 **/
@Slf4j
public class PropertiesUtil {

    private PropertiesUtil(){}

    /**
     * 读取Properties文件
     *
     * @return
     * @throws IOException
     */
    public static Properties getProperties(String filename) {
        InputStream in = null;
        Properties props = new Properties();
        try {
            File initfile = seachFile(filename);
            if (initfile.exists()) {
                in = new FileInputStream(initfile);
            } else {
                in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filename);
                if (in == null) {
                    in = new FileInputStream(initfile);
                }
            }
            props.load(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return props;
    }

    /**
     * 获取项目中的某个文件目录
     *
     * @param filename
     * @return
     */
    public static File seachFile(String filename) {
        File initfile = new File(filename);
        if (!initfile.exists()) {
            String path = PropertiesUtil.class.getResource("/").getFile().substring(1) + filename;
            initfile = new File(path);
        }
        return initfile;
    }

}
