package com.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.samskivert.mustache.Mustache;

@SpringBootApplication
public class K8smonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(K8smonitoringApplication.class, args);
	}
	
//	@Bean
//    public Mustache.Compiler mustacheCompiler(Mustache.TemplateLoader templateLoader, Environment environment) {
//
//        MustacheEnvironmentCollector collector = new MustacheEnvironmentCollector();
//        collector.setEnvironment(environment);
//        return Mustache.compiler();
//        return Mustache.compiler()
//          .defaultValue("Some Default Value")
//          .withLoader(templateLoader)
//          .withCollector(collector);

//    }
}
