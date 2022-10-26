package client.common.util;

import client.common.logs.LogBuilder;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @author yxl
 * @date: 2022/10/25 下午4:33
 */

@Component
public class FileCommon {

    private static final Logger logger = LogUtil.getLogger(FileCommon.class);

    private final FileUtil fileUtil = new FileUtil();

    public String getTokenFromLocal() {
        if (!fileUtil.isExist(FinalData.LOCAL_TOKEN_NAME)) {
            logger.info(LogBuilder.initLog(LogMsg.FILE, OptionDetails.READ_FILE_NOT_EXIST)
                    .build("fileUrl", FinalData.LOCAL_TOKEN_NAME).log());
            return null;
        }
        List<String> strings = fileUtil.readFile(FinalData.LOCAL_TOKEN_NAME);
        if (strings.size() == 0) {
            logger.warn(LogBuilder.initLog(LogMsg.FILE, OptionDetails.READ_FILE_IS_NULL)
                    .build("fileUrl", FinalData.LOCAL_TOKEN_NAME).log());
            return null;
        }
        String token = strings.get(0);
        if (token == null)
            logger.warn(LogBuilder.initLog(LogMsg.FILE, OptionDetails.READ_FILE_IS_NULL)
                    .build("fileUrl", FinalData.LOCAL_TOKEN_NAME).log());
        return token;
    }

    public void loadTokenToLocal(String token) {
        if (token == null)
            return;
        File f = fileUtil.createFile(FinalData.LOCAL_TOKEN_NAME);
        fileUtil.writeFile(f, token);
        logger.info(LogBuilder.initLog(LogMsg.FILE, OptionDetails.WRITE_FILE_TOKEN_OK)
                .build("fileUrl", FinalData.LOCAL_TOKEN_NAME)
                .build("token", token).log());
    }
}
