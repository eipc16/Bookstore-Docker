package org.pietrzakp.proxyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ProxyServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProxyServiceApplication.class, args);
	}

	@Overr
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProxyServiceApplication.class);
	}

}
