package com.project.Bank_star.config;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ServiceInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> serviceInfo = new HashMap<>();
        serviceInfo.put("name", "Star-Bank");
        serviceInfo.put("version", getClass().getPackage().getImplementationVersion() != null ?
                getClass().getPackage().getImplementationVersion() : "1.0.0");

        builder.withDetail("service", serviceInfo);
    }
}


