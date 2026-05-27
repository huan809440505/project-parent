package com.hyl.rock.system.controller;

import com.hyl.rock.base.Result;
import com.hyl.rock.web.controller.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "测试控制器")
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMQ")
    public Result<String> sendMQ(){
        rabbitTemplate.convertAndSend("direct_exchange", "direct_routing_key", "Hello, World!");
        log.info("已经发送消息");
        return success();
    }
}
