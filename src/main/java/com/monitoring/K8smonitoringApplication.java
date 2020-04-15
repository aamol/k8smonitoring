package com.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.samskivert.mustache.Mustache;

@SpringBootApplication
@EnableScheduling
public class K8smonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(K8smonitoringApplication.class, args);
	}
	
	@Bean
    public Mustache.Compiler mustacheCompiler(Mustache.TemplateLoader templateLoader, Environment environment) {

        MustacheEnvironmentCollector collector = new MustacheEnvironmentCollector();
        collector.setEnvironment(environment);
        return Mustache.compiler()
          .defaultValue(" ")
          .withLoader(templateLoader)
          .withCollector(collector);
    }
}
