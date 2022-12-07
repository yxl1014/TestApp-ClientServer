package client.ly.controller;

import client.common.util.ProtocolUtil;
import client.ly.service.SendHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pto.TestProto;


@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    SendHttp sendHttp;


    //  private final RankProto.rank.Builder rankBuilder = RankProto.rank.newBuilder();


    @PostMapping("/login")
    public String Login(@RequestParam String un, @RequestParam String pwd, @RequestParam int type) {
        TestProto.User.Builder builder = TestProto.User.newBuilder();
        builder.setUserTel(un);
        builder.setUserPassword(pwd);
        TestProto.C2S_Login.Builder builder1 = TestProto.C2S_Login.newBuilder();
        builder1.setLoginType(type);
        builder1.setUser(builder.buildPartial());
        byte[] bytes = builder1.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_LOGIN);
        return sendHttp.sendHttp_login(bytess, "login");
    }

    @PostMapping("/register")
    public String Register(@RequestParam String name, @RequestParam String tel, @RequestParam String pwd) {
        TestProto.User.Builder builder = TestProto.User.newBuilder();
        builder.setUserName(name);
        builder.setUserTel(tel);
        builder.setUserPassword(pwd);
        TestProto.C2S_Register.Builder builder1 = TestProto.C2S_Register.newBuilder();
        builder1.setUser(builder.buildPartial());
        byte[] bytes = builder1.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_REGISTER);
        return sendHttp.sendHttp_Register(bytess, "register");
    }

    @PostMapping("/updatePwdById")
    public String updatePwdById(@RequestParam int id, @RequestParam String pwd) {
        TestProto.User.Builder builder = TestProto.User.newBuilder();
        builder.setUserId(id);
        builder.setUserPassword(pwd);
        TestProto.C2S_UpdatePwd.Builder builder1 = TestProto.C2S_UpdatePwd.newBuilder();
        builder1.setUser(builder.buildPartial());
        byte[] bytes = builder1.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_UPDATEPWD);
        return sendHttp.sendHttp_updatepwd(bytess, "updatePwdById");
    }

    @PostMapping("/updateAllById")
    public String updateAllById(@RequestParam String userName, @RequestParam String userIp, @RequestParam String userPos,
                                @RequestParam String userCompany, @RequestParam String userHome, @RequestParam int userId) {
        TestProto.User.Builder builder = TestProto.User.newBuilder();
        builder.setUserName(userName);
        builder.setUserIp(userIp);
        builder.setUserPos(userPos);
        builder.setUserCompany(userCompany);
        builder.setUserHome(userHome);
        builder.setUserId(userId);
        TestProto.C2S_UpdateAll.Builder builder1 = TestProto.C2S_UpdateAll.newBuilder();
        builder1.setUser(builder.buildPartial());
        byte[] bytes = builder1.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_UPDATEALL);
        return sendHttp.sendHttp_updatepwd(bytess, "updateAllById");
    }

    @PostMapping("/updateEmailById")
    public String updateEmailById(@RequestParam int id, @RequestParam String email) {
        TestProto.User.Builder builder = TestProto.User.newBuilder();
        builder.setUserId(id);
        builder.setUserEmail(email);
        TestProto.C2S_UpdateTel.Builder builder1 = TestProto.C2S_UpdateTel.newBuilder();
        builder1.setUser(builder.buildPartial());
        byte[] bytes = builder1.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_UPDATEEMAIL);
        return sendHttp.sendHttp_updateEmailByTle(bytess, "updateEmailById");
    }

    @PostMapping("/updateTelById")
    public String updateTelById(@RequestParam int id, @RequestParam String tel) {
        TestProto.User.Builder builder = TestProto.User.newBuilder();
        builder.setUserId(id);
        builder.setUserTel(tel);
        TestProto.C2S_UpdateTel.Builder builder1 = TestProto.C2S_UpdateTel.newBuilder();
        builder1.setUser(builder.buildPartial());
        byte[] bytes = builder1.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_UPDATETEL);
        return sendHttp.sendHttp_updateEmailByTle(bytess, "updateTelById");
    }

    //绑定邮箱
    @PostMapping("/bindMailbox")
    public String bindMailbox(@RequestParam int id, @RequestParam String email) {
        TestProto.User.Builder builder = TestProto.User.newBuilder();
        builder.setUserId(id);
        builder.setUserEmail(email);
        TestProto.C2S_BindMailBox.Builder builder1 = TestProto.C2S_BindMailBox.newBuilder();
        builder1.setUser(builder.build());
        byte[] bytes = builder1.build().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_BINDMAILBOX);
        return sendHttp.sendHttp_bindMailbox(bytess, "bindMailbox");
    }

    //验证邮箱
    @PostMapping("/checkMailbox")
    public String checkMailbox(@RequestParam int id, @RequestParam String email, @RequestParam String code) {
        TestProto.User.Builder builder = TestProto.User.newBuilder();
        builder.setUserId(id);
        builder.setUserEmail(email);

        TestProto.C2S_CheckMailBox.Builder builder1 = TestProto.C2S_CheckMailBox.newBuilder();
        builder1.setUser(builder.build());
        builder1.setCode(code);
        byte[] bytes = builder1.build().toByteArray();
//        System.out.println(bytes.length);
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_CHECKMAILBOX);
        return sendHttp.sendHttp_checkMailbox(bytess, "checkMailbox");
    }
}
