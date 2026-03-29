package com.example.goldenhosewarehouse.dal.service.impl;



import com.example.goldenhosewarehouse.dal.domain.AssetEntity;
import com.example.goldenhosewarehouse.dal.repository.AssetRepository;
import com.example.goldenhosewarehouse.dal.service.AssetService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {


    private final AssetRepository assetRepository;

    // Constructor injection: Spring automatically provides the Repository
    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    /**
     * [Q1] Implements the business requirement to list all available assets.
     * It calls the native query in the DAL to fetch limited info (IDs).
     */
    @Override
    public List<String> getAllAssetIdentifiers() {
        return assetRepository.findAllAssetIds();
    }

    /**
     * [Q2]to fetch full asset details.
     * If the asset is not found, it handles the error gracefully.
     */
    @Override
    public AssetEntity getAssetById(String id) {
        return assetRepository.findLatest(id)
                .orElseThrow(() -> new RuntimeException("Asset with ID " + id + " not found in the warehouse"));
    }
    @Override
    public AssetEntity saveAsset(AssetEntity asset) {
        // In Cassandra, save() acts as both "Insert" and "Update" (Upsert)
        if (asset.getSystemDate() == null) {
            asset.setSystemDate(java.time.Instant.now());
        }
        return assetRepository.save(asset);
    }
    @Override
    public List<AssetEntity> getAllHistoryById(String id) {
        return assetRepository.findAllById(id);
    }

    @Override
    public AssetEntity getLatestAssetById(String id) {
        return assetRepository.findFirstById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found with id: " + id));
    }

    @Override
    public Slice<AssetEntity> getAllAssets(Pageable pageable) {
        return assetRepository.findAll(pageable);
    }


}