package client.ly.controller;


import client.common.resource.PublicData;
import client.ly.service.SendHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {
    SendHttp sendHttp=new SendHttp();

    //  private final RankProto.rank.Builder rankBuilder = RankProto.rank.newBuilder();

    @Autowired
    private PublicData publicData;

    private final String url1= "http://" + publicData.getLOGIN_SERVER_IP() + ":" + publicData.getLOGIN_SERVER_PORT() + "/main";






}
