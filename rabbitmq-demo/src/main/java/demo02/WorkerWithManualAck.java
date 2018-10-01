package demo02;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class WorkerWithManualAck {

    private static String QUEUE_NAME = "demo02";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // 用localhost，默认会用 guest/guest 用户名和密码
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 注意理解后面的4个参数
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 定义收到队列消息后的回调
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                try {
                    System.out.println("     processing....");
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(" [x] Done");
                    channel.basicAck(envelope.getDeliveryTag(), false); // 手动ack
                }
            }

        };
        boolean autoAck = false;
        // 监听队列
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

}
