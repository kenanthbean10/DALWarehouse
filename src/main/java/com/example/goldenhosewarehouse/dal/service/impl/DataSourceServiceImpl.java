package com.example.goldenhosewarehouse.dal.service.impl;

import com.example.goldenhosewarehouse.dal.domain.DataSourceEntity;
import com.example.goldenhosewarehouse.dal.repository.DataSourceRepository;
import com.example.goldenhosewarehouse.dal.service.DataSourceService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DataSourceServiceImpl implements DataSourceService {
    private final DataSourceRepository dataSourceRepository;

    public DataSourceServiceImpl(DataSourceRepository dataSourceRepository) {
        this.dataSourceRepository = dataSourceRepository;
    }

    @Override
    public List<String> getAllSourceIdentifiers() {
        return dataSourceRepository.findAllSourceIds();
    }

    @Override
    public DataSourceEntity createSource(DataSourceEntity source) {
        return dataSourceRepository.save(source);
    }
    @Override
    public DataSourceEntity getSourceById(String id) {
        return dataSourceRepository.findLatest(id)
                .orElseThrow(() -> new RuntimeException("Data Source with ID " + id + " not found."));
    }
}
