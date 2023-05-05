package appmanager;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.AMQP.BasicProperties;

public class P2PPublisher extends HelperBase{
    public P2PPublisher(ApplicationManager app) {
        super(app);
    }

    public void publish(String exchange, String queue, String message) throws IOException, TimeoutException, InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(app.getProperty("rabbitmq.host"));
        connectionFactory.setVirtualHost(app.getProperty("rabbitmq.VirtualHost"));
        connectionFactory.setPort(Integer.parseInt(app.getProperty("rabbitmq.port")));
        connectionFactory.setUsername(app.getProperty("rabbitmq.username"));
        connectionFactory.setPassword(app.getProperty("rabbitmq.password"));

        BasicProperties props = new BasicProperties.Builder()
                                .messageId(UUID.randomUUID().toString())
                                .build();

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.basicPublish(exchange, queue, props, message.getBytes());

        channel.close();
        connection.close();
    }
}

