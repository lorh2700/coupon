package com.kakao.dy;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class ServiceEnvironmentPostProcessor implements EnvironmentPostProcessor {
	
	private static final String DEFAULT_MY_FRAMEWORK_PROPERTIES = "CouponApiProperties";
    
	private static final String DEFAULT_MY_FRAMEWORK_PROPERTIES_LOCATION = "/configuration/properties/properties.yml";

    private YamlPropertySourceLoader propertySourceLoader = new YamlPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
      
    	Resource resource = new ClassPathResource(DEFAULT_MY_FRAMEWORK_PROPERTIES_LOCATION);
        load(environment, resource);
    }

    private void load(ConfigurableEnvironment environment, Resource resource) {
        String propertyName = DEFAULT_MY_FRAMEWORK_PROPERTIES;
    	try {
    		PropertySource<?> propertySource = propertySourceLoader.load(propertyName, resource, null);
            if (propertySource != null) {
                environment.getPropertySources().addLast(propertySource);
            }
        } catch (IOException ex) {
        	throw new IllegalStateException("Failed to load yaml configuration from " + resource, ex);
        }
    }
    
}
