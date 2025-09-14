package com.pearl.warehouse.controller;

import com.pearl.warehouse.components.DatasourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/configuration")
public class ConfigurationPropertiesController {


    @Value("${spring.datasource.url:mysqlUrl}")
    String datasource;


    private final DatasourceProperties datasourceProperties;

    public ConfigurationPropertiesController(DatasourceProperties datasourceProperties){
        this.datasourceProperties = datasourceProperties;
    }

    @GetMapping("/databaseUrl")
    public String getDatabaseUrl(){
        return datasource;
    }

    @GetMapping("/datasource")
    public DatasourceProperties getDatasourceProperties(){
        return datasourceProperties;
    }


}
