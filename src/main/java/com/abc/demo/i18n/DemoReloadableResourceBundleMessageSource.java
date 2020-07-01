package com.abc.demo.i18n;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class DemoReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

    public Properties getPropertiesByFileName(String fileName) {
        return super.getProperties(fileName).getProperties();
    }

}
