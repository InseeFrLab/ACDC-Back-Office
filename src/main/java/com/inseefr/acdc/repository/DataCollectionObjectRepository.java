package com.inseefr.acdc.repository;

import com.inseefr.acdc.model.DataCollectionObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCollectionObjectRepository extends JpaRepository<DataCollectionObject, String> {

}
