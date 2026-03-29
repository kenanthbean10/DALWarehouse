package com.example.goldenhosewarehouse.dal.service;

import com.example.goldenhosewarehouse.dal.domain.DataSourceEntity;

import java.util.List;

public interface DataSourceService {

    // [Q3] Returns a list of all available data source identifiers
    List<String> getAllSourceIdentifiers();

    // [Q4] Returns full configuration details for a specific data source
    DataSourceEntity getSourceById(String id);

    // DataSourceService.java (Interface)
    DataSourceEntity createSource(DataSourceEntity source);

}
