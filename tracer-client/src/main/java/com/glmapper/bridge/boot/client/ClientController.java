package com.glmapper.bridge.boot.client;

import com.glmapper.bridge.boot.facade.SampleFacade;
import com.glmapper.bridge.boot.instance.HttpAsyncClientInstance;
import com.glmapper.bridge.boot.instance.HttpClientInstance;
import com.glmapper.bridge.boot.instance.OkHttpClientInstance;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: glmapper (glmapper_2018@163.com) 2020/4/18 3:44 PM
 * @since:
 **/
@RestController
@RequestMapping("api")
public class ClientController {

    private static Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Reference(timeout = 5000)
    public SampleFacade sampleFacade;

    @RequestMapping("client")
    public String testClient(@RequestParam String type) throws Throwable{
        String result = sampleFacade.SayHello("hello SOFAStack!");
        switch (type){
            case "restTemplate":
                return doTestRestTemplate()+" " + result;
            case "httpcleint":
                return doTestHttpClient()+" " + result;
            case "okhttp":
                return doTestOkHttp()+" " + result;
        }
        return "DEFAULT";
    }

    private String doTestOkHttp() throws Exception {
        OkHttpClientInstance httpClient = new OkHttpClientInstance();
        String httpGetUrl = "http://localhost:8082/api/server";
        String responseStr = httpClient.executeGet(httpGetUrl);
        logger.info("Response is {}", responseStr);
        return responseStr;
    }

    private String doTestHttpClient() throws Exception {
        HttpClientInstance httpClientInstance = new HttpClientInstance(10 * 1000);
        String httpGetUrl = "http://localhost:8082/api/server";
        //sync
        String responseStr = httpClientInstance.executeGet(httpGetUrl);
        logger.info("Response is {}", responseStr);
        //async
        String asyncResponseStr = new HttpAsyncClientInstance().executeGet(httpGetUrl);
        logger.info("Async Response is {}", asyncResponseStr);
        return responseStr;
    }

    private String doTestRestTemplate() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "http://localhost:8082/api/server", String.class);
        logger.info("Response is {}", responseEntity.getBody());
        return responseEntity.getBody();
    }
}
