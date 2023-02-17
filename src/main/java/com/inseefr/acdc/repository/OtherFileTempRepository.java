package com.inseefr.acdc.repository;
import com.inseefr.acdc.domain.OtherFileTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherFileTempRepository extends JpaRepository<OtherFileTemp, String> {

}
