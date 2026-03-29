package com.example.goldenhosewarehouse.api.web;

import com.example.goldenhosewarehouse.dal.domain.DataSourceEntity;
import com.example.goldenhosewarehouse.dal.service.DataService;
import com.example.goldenhosewarehouse.dal.service.DataSourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sources")
public class DataSourceController {

private final DataSourceService dataSourceService;


    public DataSourceController( DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }
/**
 * [Q3] Endpoint to list all data source provider IDs
 * URL: GET /api/sources
 */
@GetMapping
public List<String> getAllSources() {
    return dataSourceService.getAllSourceIdentifiers();
}

    @GetMapping("/{id}")
    public DataSourceEntity getSourceById(@PathVariable String id) {
        return dataSourceService.getSourceById(id);
    }

    // DataSourceController.java
    @PostMapping
    public DataSourceEntity createSource(@RequestBody DataSourceEntity source) {
        return dataSourceService.createSource(source);
    }

}
