package com.chen.trademark.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

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

    public String getUSPTO() {
        return USPTO;
    }

    public void setUSPTO(String USPTO) {
        this.USPTO = USPTO;
    }

    public String getUK() {
        return UK;
    }

    public void setUK(String UK) {
        this.UK = UK;
    }

    public String getEUIPO() {
        return EUIPO;
    }

    public void setEUIPO(String EUIPO) {
        this.EUIPO = EUIPO;
    }

    public String getDPMA() {
        return DPMA;
    }

    public void setDPMA(String DPMA) {
        this.DPMA = DPMA;
    }

    public String getINPI() {
        return INPI;
    }

    public void setINPI(String INPI) {
        this.INPI = INPI;
    }

    public String getOEPM() {
        return OEPM;
    }

    public void setOEPM(String OEPM) {
        this.OEPM = OEPM;
    }

    public String getJ_PLATPAT() {
        return J_PLATPAT;
    }

    public void setJ_PLATPAT(String j_PLATPAT) {
        J_PLATPAT = j_PLATPAT;
    }
}

