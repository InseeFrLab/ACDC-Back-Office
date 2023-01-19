package com.inseefr.acdc.repository;

import com.inseefr.acdc.model.DataCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface DataCollectionRepository extends JpaRepository<DataCollection, String> {

}
