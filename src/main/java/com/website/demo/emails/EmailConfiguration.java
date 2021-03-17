package com.website.demo.emails;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmailConfiguration {


    @Value("{$spring.mail.host}")
    private String host;

    @Value("{$spring.mail.port}")
    private String port;

    @Value("{$spring.mail.username}")
    private String username;

    @Value("{$spring.mail.password}")
    private String password;



}
