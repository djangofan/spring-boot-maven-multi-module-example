package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class Application {

	private static Path CONFIG = Paths.get(System.getProperty("user.dir"))
			.resolve(Paths.get(".."))
			.resolve("config");

	private static final String EXT_CONFIG_DIR = CONFIG.toString() + File.separator;

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
		Resource[] resources = new Resource[] {
				        new FileSystemResource(EXT_CONFIG_DIR + "application-prd.properties"),
						new FileSystemResource(EXT_CONFIG_DIR + "application-dev.properties"),
						new ClassPathResource("/application.properties")
				};
		log.info("Properties: " + Arrays.deepToString(resources));
		properties.setIgnoreResourceNotFound(true);
		properties.setLocations(resources);
		return properties;
	}

}
