package com.chen.trademark.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:url.properties")
@ConfigurationProperties(prefix="url")
public class UrlConfig {
    private String USPTO;

    private String UK;

    private String EUIPO;

    private String DPMA;

    private String INPI;

    private String OEPM;

    private String J_PLATPAT;

    private String UIBM;

    private String OEPM_R;

    private String WIPO;
}

