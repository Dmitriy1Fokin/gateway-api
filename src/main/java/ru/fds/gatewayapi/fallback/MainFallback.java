package ru.fds.gatewayapi.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import ru.fds.gatewayapi.response.GatewayClientResponse;

@Slf4j
@Component
public class MainFallback implements FallbackProvider {


    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        String message = "the service is not available";
        if (cause instanceof HystrixTimeoutException) {
            return new GatewayClientResponse(HttpStatus.GATEWAY_TIMEOUT, message);
        } else {
            return new GatewayClientResponse(HttpStatus.INTERNAL_SERVER_ERROR, message);
        }
    }
}
