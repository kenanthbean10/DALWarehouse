package com.example.goldenhosewarehouse.dal.service.impl;

import com.example.goldenhosewarehouse.dal.domain.DataEntity;
import com.example.goldenhosewarehouse.dal.repository.DataKey;
import com.example.goldenhosewarehouse.dal.repository.DataRepository;
import com.example.goldenhosewarehouse.dal.service.DataService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class DataServiceImpl implements DataService {
private DataRepository dataRepository;

    public DataServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public Iterable<DataEntity> getTimeSeries(String assetId, String sourceId) {
    //create the composite key required by the DAL
        DataKey key = new DataKey(assetId, sourceId);
        return dataRepository.findAll(key);

    }
//filtered time-series analysis
    //so we can generate reports for specific months or year
    @Override
    public Iterable<DataEntity> getTimeSeriesByRange(String assetId, String sourceId, LocalDate start, LocalDate end) {
        DataKey key = new DataKey(assetId, sourceId);
        return dataRepository.findByRange(key, start, end);
    }
/**
 * [Q5] Implementation to retrieve full historical data.
 * It encapsulates the creation of DataKey to keep the Controller clean.
 */
@Override
public DataEntity saveData(DataEntity dataRecord) {

    if (dataRecord.getSystemDate() == null) {
        dataRecord.setSystemDate(java.time.Instant.now());


    }
    // يمكنك إضافة Business Logic هنا (مثلاً: التحقق من صحة القيمة قبل الحفظ)
    return dataRepository.save(dataRecord);
}
    // الميثود الجديدة لتنفيذ الـ Pagination
    @Override
    public Slice<DataEntity> getAllDataPaged(int page, int size) {
        // نمرر طلب الصفحة إلى الـ Repository اليدوي الذي يحتوي على ميثود findAll(Pageable)
        return dataRepository.findAll(PageRequest.of(page, size));
    }

}
