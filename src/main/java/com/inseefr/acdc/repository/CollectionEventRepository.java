package com.inseefr.acdc.repository;

import com.inseefr.acdc.model.CollectionEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public interface CollectionEventRepository extends JpaRepository<CollectionEvent, String> {


}
