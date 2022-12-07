package client.ly.controller;


import client.common.util.ProtocolUtil;
import client.ly.service.impl.ConsumerSendImpl;
import client.ly.service.impl.ProducerSendImpl;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pto.TestProto;

@RestController
@RequestMapping("/main")
public class MainController {

    //  private final RankProto.rank.Builder rankBuilder = RankProto.rank.newBuilder();


    @Autowired
    private ConsumerSendImpl consumerSend;

    @Autowired
    private ProducerSendImpl producerSend;


    @PostMapping("/consGetTask")
    public TestProto.Task consGetTask(@RequestBody int id) {
        TestProto.C2S_Get_Task.Builder builder = TestProto.C2S_Get_Task.newBuilder();
        builder.setTaskId(id);
        byte[] bytes = builder.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_GET_TASK);
        return consumerSend.getTask(bytess, "/getTaskInformation");
    }

    @PostMapping("/consTakeTask")
    public TestProto.ResponseMsg consTakeTask(@RequestBody int userId, @RequestBody int taskId) {
        TestProto.C2S_Cons_TakeTask.Builder builder = TestProto.C2S_Cons_TakeTask.newBuilder();
        builder.setUserId(userId);
        builder.setTaskId(taskId);
        byte[] bytes = builder.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_CONS_TAKE_TASK);
        return consumerSend.takeTask(bytess, "/ConsTakeTask");
    }

    @PostMapping("/consStartTask")
    public TestProto.ResponseMsg consStartTask(@RequestBody int userId, @RequestBody int taskId) {
        TestProto.C2S_Cons_StartTask.Builder builder = TestProto.C2S_Cons_StartTask.newBuilder();
        builder.setUserId(userId);
        builder.setTaskId(taskId);
        byte[] bytes = builder.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_CONS_START_TASK);
        return consumerSend.startTask(bytess, "/consStartTask", taskId);
    }

    @PostMapping("/consEndTask")
    public TestProto.ResponseMsg consEndTask(@RequestBody int userId, @RequestBody int taskId) {
        TestProto.C2S_Cons_EndTask.Builder builder = TestProto.C2S_Cons_EndTask.newBuilder();
        builder.setUserId(userId);
        builder.setTaskId(taskId);
        byte[] bytes = builder.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_CONS_END_TASK);
        return consumerSend.endTask(bytess, "/consEndTask");
    }

    @PostMapping("/consDelTask")
    public TestProto.ResponseMsg consDelTask(@RequestBody int userId, @RequestBody int taskId) {
        TestProto.C2S_Cons_DelTask.Builder builder = TestProto.C2S_Cons_DelTask.newBuilder();
        builder.setUserId(userId);
        builder.setTaskId(taskId);
        byte[] bytes = builder.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_CNS_DEL_TASK);
        return consumerSend.delTask(bytess, "/consDelTask");
    }


    @PostMapping("/consGetAllTask")
    public TestProto.ConsGetTasks consGAllTask(@RequestBody int id) {
        TestProto.C2S_Cons_AllGetTasks.Builder builder = TestProto.C2S_Cons_AllGetTasks.newBuilder();
        builder.setUserId(id);
        byte[] bytes = builder.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_CONS_ALL_GET_TASKS);
        return consumerSend.allGetTask(bytess, "/consAllGetTasks");
    }


    @PostMapping("/prodAddTask")
    public TestProto.ResponseMsg prodAddTask(@RequestBody byte[] data) throws InvalidProtocolBufferException {
        if (data == null) {
            return null;
        }
        TestProto.C2S_prodAddTask.Builder builder = TestProto.C2S_prodAddTask.newBuilder();
        builder.setTask(TestProto.Task.parseFrom(data));
        byte[] bytes = builder.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_PROD_ADD_TASK);
        return producerSend.addTask(bytess, "/release");
    }

    @PostMapping("/prodStartTask")
    public TestProto.ResponseMsg prodStartTask(@RequestBody int id) {
        TestProto.C2S_ProdStartTask.Builder builder = TestProto.C2S_ProdStartTask.newBuilder();
        builder.setTaskId(id);
        byte[] bytes = builder.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_PROD_START_TASK);
        return producerSend.startTask(bytess, "/Start", id);
    }

    @PostMapping("/prodEndTask")
    public TestProto.ResponseMsg prodEndTask(@RequestBody int id) {
        TestProto.C2S_prod_EndTask.Builder builder = TestProto.C2S_prod_EndTask.newBuilder();
        builder.setTaskId(id);
        byte[] bytes = builder.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_PROD_END_TASK);
        return producerSend.endTask(bytess, "/close");
    }

    @PostMapping("/prodGetTaskResults")
    public TestProto.TaskResult prodGetTaskResults(@RequestBody int id) {
        TestProto.C2S_prod_GetResult.Builder builder = TestProto.C2S_prod_GetResult.newBuilder();
        builder.setTaskId(id);
        byte[] bytes = builder.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_PROD_GETRESULT);
        return producerSend.getTaskResults(bytess, "/GetTestResults");
    }

    @PostMapping("/prodAllGetTask")
    public TestProto.ProdAddTasks prodAllGetTask(@RequestBody int id) {
        TestProto.C2S_prod_GetAllAddTasks.Builder builder = TestProto.C2S_prod_GetAllAddTasks.newBuilder();
        builder.setUserId(id);
        byte[] bytes = builder.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_PROD_GET_ALL_ADD_TASKS);
        return producerSend.allGetTask(bytess, "/queryAllAddTasks");
    }


}
