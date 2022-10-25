package client.common.util;

import client.common.logs.LogUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author yxl
 * @date: 2022/10/25 下午4:27
 */

public class FileUtil {

    private static final Logger logger = LogUtil.getLogger(FileUtil.class);

    private static final String path = Objects.requireNonNull(FileUtil.class.getResource("/")).getPath();//获取当前路径

    public List<String> readFile(String filename) {
        File file = new File(path + filename);//创建文件类
        BufferedReader reader = null;
        StringBuilder data = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            //按行读，并把每次读取的结果保存在line字符串中
            while ((line = reader.readLine()) != null) {
                data.append(line);
                System.out.println(line);
            }
            //关闭流
            reader.close();
        } catch (IOException e) {
            logger.warn(e);
        } finally {
            //当由于异常情况，上面关闭流程序没有执行时
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.warn(e);
                }
            }
        }

        String[] data1 = data.toString().split("_EOF_");
        List<String> datas = new ArrayList<>();
        Collections.addAll(datas, data1);
        return datas;
    }

    public boolean writeFile(File f, String data) {
        boolean isok;

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(f));
            writer.write(data + "_EOF_");
            //关闭流
            writer.close();
            isok = true;
        } catch (IOException e) {
            logger.warn(e);
            isok = false;//抛错，并
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.warn(e);
                }
            }
        }
        return isok;
    }

    public boolean appendFile(File f, String data) {
        boolean isok;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(f, true));
            writer.write(data + "_EOF_");
            //关闭流
            writer.close();
            isok = true;
        } catch (IOException e) {
            logger.warn(e);
            isok = false;//抛错，并
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.warn(e);
                }
            }
        }
        return isok;
    }

    public File createFile(String filename) {
        File f = new File(path + filename);//创建文件类
        if (!f.exists()) {//判断该文件是否存在
            try {
                f.createNewFile();//不存在则创建新文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return f;
    }

    public File createTempFile(String filename) {
        File f = new File(path + filename);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                logger.warn(e);
            }
        }
        f.deleteOnExit();//设置虚拟机关闭时删除该文件
        return f;
    }

    public boolean isExist(String filename) {
        File f = new File(path + filename);
        return f.exists();
    }
}
