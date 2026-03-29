package com.example.goldenhosewarehouse.dal.service;

import com.example.goldenhosewarehouse.dal.domain.AssetEntity;
import com.example.goldenhosewarehouse.dal.domain.DataEntity;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;

public interface DataService {

    // [Q5] Business logic to get all time-series data for an asset and source

    Iterable<DataEntity> getTimeSeries(String assetId, String sourceId);

    // Business logic for analytical range queries
    Iterable<DataEntity> getTimeSeriesByRange(String assetId, String sourceId, LocalDate start, LocalDate end);
    DataEntity saveData(DataEntity dataRecord);
    Slice<DataEntity> getAllDataPaged(int page, int size);
}
