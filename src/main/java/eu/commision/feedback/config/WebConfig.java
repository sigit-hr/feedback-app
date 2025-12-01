package eu.commision.feedback.config;

import eu.commision.feedback.entity.ContactType;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(
        String.class,
        ContactType.class,
        source -> source == null ? null : ContactType.valueOf(source.trim().toUpperCase()));
  }
}
