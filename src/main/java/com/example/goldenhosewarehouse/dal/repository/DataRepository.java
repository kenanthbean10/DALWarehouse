package com.example.goldenhosewarehouse.dal.repository;

import com.example.goldenhosewarehouse.dal.domain.DataEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
// تأكد من وجود مسافة بين extends و CassandraRepository
public interface DataRepository extends CassandraRepository<DataEntity, DataKey>, WarehouseRepository<DataEntity, DataKey> {

    /**
     * [Q5] Time Series Range Query
     * جلب البيانات ضمن نطاق زمني معين.
     */
    Iterable<DataEntity> findByRange(DataKey key, LocalDate startDate, LocalDate endDate);

    /**
     * الحفظ اليدوي (موروث من WarehouseRepository)
     */
    @Override
    DataEntity save(DataEntity entity);

    /**
     * تفعيل الـ Pagination (مطلوب في صفحة 2 من المستند)
     */
    @Override
    Slice<DataEntity> findAll(Pageable pageable);
}