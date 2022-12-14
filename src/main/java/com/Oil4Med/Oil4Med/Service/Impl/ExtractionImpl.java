package com.Oil4Med.Oil4Med.Service.Impl;

import com.Oil4Med.Oil4Med.Model.Extraction;
import com.Oil4Med.Oil4Med.Repository.ExtractionRepository;
import com.Oil4Med.Oil4Med.Service.ExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ExtractionImpl implements ExtractionService {

    @Autowired
    ExtractionRepository extractionRepository;
    @Override
    public List<Extraction> getExtractions() {
        List<Extraction> extractions = new ArrayList<>();
        extractionRepository.findAll().forEach(extractions::add);
        return extractions;
    }

    @Override
    public Extraction getExtractionById(Long id) {
        return extractionRepository.findById(id).get();
    }

    @Override
    public Extraction addExtraction(Extraction extraction) {
        return extractionRepository.save(extraction);
    }

    @Override
    public void deleteExtraction(Extraction extraction) {
        extractionRepository.delete(extraction);
    }

    @Override
    public void updateExtraction(Long extractionId, Extraction newExtraction) {
        Extraction extraction = extractionRepository.findById(extractionId).get();
        extraction.setExtractionStatus(newExtraction.getExtractionStatus());
        extraction.setStartDate(newExtraction.getStartDate());
        extraction.setFinishDate(newExtraction.getFinishDate());
        extractionRepository.save(extraction);
    }
}
