package com.hyl.rock.system.consumer;



import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class RabbitConsumer {

    // 监听 Direct 队列（队列名称：direct_queue）
    @RabbitListener(queues = "direct_queue")
    public void receiveDirectMessage(String message, Channel channel, Message msg) throws IOException {
        try {
            // 1. 处理业务逻辑（如订单状态更新、库存扣减等）
            log.info("Direct 消费者接收消息：{}", message);

            // 2. 手动确认消息（Ack）：告知 RabbitMQ 消息已处理完成，可删除
            // 第二个参数：multiple=false（仅确认当前消息）
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 3. 消息处理失败：拒绝消息并重回队列（或转发到死信队列，根据业务调整）
            // 第二个参数：multiple=false；第三个参数：requeue=true（重回队列）
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
            log.error("Direct 消息处理失败", e);
        }
    }

}
