package com.inseefr.acdc.services;
import com.inseefr.acdc.domain.OtherFileTemp;
import com.inseefr.acdc.repository.OtherFileTempRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OtherFileTempService {
    @Autowired
    OtherFileTempRepository otherFileTempRepository;

    public List<OtherFileTemp> getAllOtherFileTemp() {
        return otherFileTempRepository.findAll();
    }

    public OtherFileTemp getOtherFileTempById(String id) {
        return otherFileTempRepository.findById(id).orElse(null);
    }

    public OtherFileTemp saveOtherFileTemp(OtherFileTemp otherFileTemp) {
        return otherFileTempRepository.save(otherFileTemp);
    }

    public void deleteOtherFileTemp(String id) {
        otherFileTempRepository.deleteById(id);
    }

    public OtherFileTemp saveAndFlushOtherFileTemp(OtherFileTemp otherFileTemp) {
        return otherFileTempRepository.saveAndFlush(otherFileTemp);
    }

    public OtherFileTemp updateOtherFileTemp(OtherFileTemp otherFileTemp) {

        return otherFileTempRepository.saveAndFlush(otherFileTemp);
    }
}
