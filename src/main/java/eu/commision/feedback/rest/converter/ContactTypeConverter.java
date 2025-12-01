package eu.commision.feedback.rest.converter;

import eu.commision.feedback.entity.ContactType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ContactTypeConverter implements Converter<String, ContactType> {

    @Override
    public ContactType convert(String source) {
        return ContactType.fromJson(source);
    }
}