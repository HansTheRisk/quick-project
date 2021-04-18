package main;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.redis")
@Getter
@Setter
public class RedisProperties {
    private int redisPort;
    private String redisHost;

    public RedisProperties(
            @Value("${port}") int redisPort,
            @Value("${host}") String redisHost) {
        this.redisPort = redisPort;
        this.redisHost = redisHost;
    }
}
