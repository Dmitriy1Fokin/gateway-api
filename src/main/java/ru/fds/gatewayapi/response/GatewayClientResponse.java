package ru.fds.gatewayapi.response;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Getter
public class GatewayClientResponse implements ClientHttpResponse {

    private final HttpStatus status;
    private final String message;

    public GatewayClientResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus getStatusCode() {
        return status;
    }

    @Override
    public int getRawStatusCode(){
        return status.value();
    }

    @Override
    public String getStatusText(){
        return status.getReasonPhrase();
    }

    @Override
    public void close() {

    }

    @Override
    public InputStream getBody(){
        return new ByteArrayInputStream(message.getBytes());
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
