package it.popso.popsogift.config;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springmvc.OpenApiValidationFilter;
import com.atlassian.oai.validator.springmvc.OpenApiValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.atlassian.oai.validator.whitelist.ValidationErrorsWhitelist;
import org.springframework.data.jpa.repository.config.*;

import javax.servlet.Filter;
import java.io.IOException;

@Configuration
public class OpenApiValidationConfig implements WebMvcConfigurer {
/*
    private final OpenApiValidationInterceptor validationInterceptor;

    @Autowired
    public OpenApiValidationConfig() throws IOException {

        this.validationInterceptor = new OpenApiValidationInterceptor(OpenApiInteractionValidator
                .createFor("/oa3/schema-v1.0.yaml").withWhitelist(
                        ValidationErrorsWhitelist.create()
                                .withRule("home", (message, operation, request, response)
                                        -> request!=null && ("/".equals(request.getPath()) ||
                                        request.getPath().startsWith("/v3/api-docs") ||
                                        request.getPath().startsWith("/swagger-ui")
                                ))
                ).build());
    }

    @Bean
    public Filter validationFilter() {
        return new OpenApiValidationFilter(
                true, // enable request validation
                true  // enable response validation
        );
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(validationInterceptor);
    }

 */
}
