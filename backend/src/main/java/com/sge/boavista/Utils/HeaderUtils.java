package com.sge.boavista.Utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Getter
@Setter
@AllArgsConstructor
public class HeaderUtils {

    private String secret;
    private String client;


    private HttpHeaders getXMLHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        return headers;
    }

    private void addCustomHeader(HttpHeaders headers, String key, String value) {
        headers.add(key, value);
    }

    public HttpHeaders get_header(){
        HttpHeaders headers = this.getXMLHeaders();
        this.addCustomHeader(headers, "chaveAcesso", this.client);
        this.addCustomHeader(headers, "chaveSecreta", this.secret);

        return headers;
    }

}
