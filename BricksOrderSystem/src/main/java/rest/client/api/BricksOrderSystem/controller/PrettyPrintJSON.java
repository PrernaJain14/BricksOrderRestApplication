package rest.client.api.BricksOrderSystem.controller;


import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;



//Found this code from Stackoverflow for pretty printing the JSON


@Configuration
public class PrettyPrintJSON extends WebMvcConfigurationSupport {

    @Override
    protected void extendMessageConverters( List<HttpMessageConverter<?>> converters ) {
        for ( HttpMessageConverter<?> converter : converters ) {
            if ( converter instanceof MappingJackson2HttpMessageConverter ) {
                MappingJackson2HttpMessageConverter jacksonConverter =
                        (MappingJackson2HttpMessageConverter) converter;
                jacksonConverter.setPrettyPrint(true);
            }
        }
    }
}