package client.testController;

import client.common.logs.LogClass;
import client.common.logs.LogMsg;
import client.common.logs.LogUtil;
import client.common.logs.OptionDetails;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yxl
 * @date: 2022/10/25 下午2:58
 */

@RestController
public class TestController {

    private final static Logger logger= LogUtil.getLogger(TestController.class);

    @GetMapping("test")
    public String test() {
        logger.info(LogClass.initLog(LogMsg.TEST, OptionDetails.TEST_OK).log());
        return "test";
    }
}
