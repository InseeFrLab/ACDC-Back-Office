package com.inseefr.acdc.repository;

import com.inseefr.acdc.model.CollectionEventObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CollectionEventObjectRepository extends JpaRepository<CollectionEventObject, String> {


}
