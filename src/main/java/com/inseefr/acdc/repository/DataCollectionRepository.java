package com.inseefr.acdc.repository;

import com.inseefr.acdc.domain.DataCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCollectionRepository extends JpaRepository<DataCollection, String> {
}
