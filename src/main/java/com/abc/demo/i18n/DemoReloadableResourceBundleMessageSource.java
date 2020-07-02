package com.abc.demo.i18n;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DemoReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

    public Properties getProperties(Locale locale) {
        return getMergedProperties(locale).getProperties();
    }

    public Set<String> getPropertiesKeySet(Locale locale) {
        Properties properties = getProperties(locale);
        return properties.keySet().stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
    }

    public void updateProperty(Locale locale, String key, String value) {
        Properties properties = getProperties(locale);
        properties.setProperty(key, value);
    }

}
