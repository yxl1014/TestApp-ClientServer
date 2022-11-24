package client.ly.controller;

import client.common.resource.PublicData;
import client.common.util.ProtocolUtil;
import client.ly.service.SendHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pto.TestProto;


@RestController
@RequestMapping("/user")
public class LoginController {
    SendHttp sendHttp=new SendHttp();

  //  private final RankProto.rank.Builder rankBuilder = RankProto.rank.newBuilder();

    @Autowired
    private PublicData publicData;

    private final String url1= "http://" + publicData.getLOGIN_SERVER_IP() + ":" + publicData.getLOGIN_SERVER_PORT() + "/user";


    @PostMapping("/login")
    public void Login(@RequestBody String un, @RequestBody String pwd) {
        TestProto.User.Builder builder = TestProto.User.newBuilder();
        builder.setUserTel(un);
        builder.setUserPassword(pwd);
        TestProto.C2S_Login.Builder builder1 = TestProto.C2S_Login.newBuilder();
        builder1.setLoginType(0);
        builder1.setUser(builder.buildPartial());
        byte[] bytes=builder1.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_LOGIN);
        for (byte b:bytes){
            System.out.print(b);
        }
        System.out.println();

        String result = sendHttp.sendHttp_login(bytess, "login");
        System.out.println(result);
    }

    @PostMapping("/register")
    public void Register(String un, String pwd){
        TestProto.User.Builder builder = TestProto.User.newBuilder();
        builder.setUserTel(un);
        builder.setUserPassword(pwd);
        TestProto.C2S_Register.Builder builder1 = TestProto.C2S_Register.newBuilder();
        builder1.setUser(builder.buildPartial());
        byte[] bytes=builder1.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_REGISTER);
        for (byte b:bytes){
            System.out.print(b);
        }
        System.out.println();

        String result = sendHttp.sendHttp_Register(bytess, "register");
        System.out.println(result);
    }
    @PostMapping("/updatePwdById")
    public void updatePwdById(int id,String pwd){
        TestProto.User.Builder builder= TestProto.User.newBuilder();
        builder.setUserId(id);
        builder.setUserPassword(pwd);
        TestProto.C2S_UpdatePwd.Builder builder1=TestProto.C2S_UpdatePwd.newBuilder();
        builder1.setUser(builder.buildPartial());
        byte[] bytes=builder1.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_UPDATEPWD);
        for (byte b:bytes){
            System.out.print(b);
        }
        System.out.println();
        String result = sendHttp.sendHttp_updatepwd(bytess,"updatePwdById");
        System.out.println(result);

    }

    @PostMapping("/updateAllById")
    public void updateAllById(String userName,String userIp,String userPos,String userCompany,String userHome,int userId){
        TestProto.User.Builder builder= TestProto.User.newBuilder();
        builder.setUserName(userName);
        builder.setUserIp(userIp);
        builder.setUserPos(userPos);
        builder.setUserCompany(userCompany);
        builder.setUserHome(userHome);
        builder.setUserId(userId);
        TestProto.C2S_UpdateAll.Builder builder1=TestProto.C2S_UpdateAll.newBuilder();
        builder1.setUser(builder.buildPartial());
        byte[] bytes=builder1.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_UPDATEALL);
        for (byte b:bytes){
            System.out.print(b);
        }
        System.out.println();
        String result = sendHttp.sendHttp_updatepwd(bytess,"updateAllById");
        System.out.println(result);
    }

    @PostMapping("/updateEmailById")
    public void updateEmailById(int id,String email){
        TestProto.User.Builder builder= TestProto.User.newBuilder();
        builder.setUserId(id);
        builder.setUserEmail(email);
        TestProto.C2S_UpdateTel.Builder builder1=TestProto.C2S_UpdateTel.newBuilder();
        builder1.setUser(builder.buildPartial());
        byte[] bytes=builder1.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_UPDATEEMAIL);
        for (byte b:bytes){
            System.out.print(b);
        }
        System.out.println();
        String result = sendHttp.sendHttp_updateEmailByTle(bytess,"updateEmailById");
        System.out.println(result);

    }

    @PostMapping("/updateTelById")
    public void updateTelById(int id,String tel){
        TestProto.User.Builder builder= TestProto.User.newBuilder();
        builder.setUserId(id);
        builder.setUserTel(tel);
        TestProto.C2S_UpdateTel.Builder builder1=TestProto.C2S_UpdateTel.newBuilder();
        builder1.setUser(builder.buildPartial());
        byte[] bytes=builder1.buildPartial().toByteArray();
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_UPDATETEL);
        for (byte b:bytes){
            System.out.print(b);
        }
        System.out.println();
        String result = sendHttp.sendHttp_updateEmailByTle(bytess,"updateTelById");
        System.out.println(result);

    }

    //绑定邮箱
    @PostMapping("/bindMailbox")
    public void bindMailbox(int id,String email){
        TestProto.User.Builder builder= TestProto.User.newBuilder();
        builder.setUserId(id);
        builder.setUserEmail(email);
        TestProto.C2S_BindMailBox.Builder builder1=TestProto.C2S_BindMailBox.newBuilder();
        builder1.setUser(builder.build());
        byte[] bytes=builder1.build().toByteArray();
//        System.out.println(bytes.length);
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_BINDMAILBOX);
        for (byte b:bytes){
            System.out.print(b);
        }
        System.out.println();
        String result = sendHttp.sendHttp_bindMailbox(bytess,"bindMailbox");
        System.out.println(result);

    }

    //验证邮箱
    @PostMapping("/checkMailbox")
    public void checkMailbox(int id,String email ,String code){
        TestProto.User.Builder builder= TestProto.User.newBuilder();
        builder.setUserId(id);
        builder.setUserEmail(email);

        TestProto.C2S_CheckMailBox.Builder builder1=TestProto.C2S_CheckMailBox.newBuilder();
        builder1.setUser(builder.build());
        builder1.setCode(code);
        byte[] bytes=builder1.build().toByteArray();
//        System.out.println(bytes.length);
        byte[] bytess = new ProtocolUtil().encodeProtocol(bytes, bytes.length, TestProto.Types.C2S_CHECKMAILBOX);
        for (byte b:bytes){
            System.out.print(b);
        }
        System.out.println();
        String result = sendHttp.sendHttp_checkMailbox(bytess,"checkMailbox");
        System.out.println(result);

    }
}
