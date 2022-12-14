package com.Oil4Med.Oil4Med.Service;

import com.Oil4Med.Oil4Med.Model.Extraction;

import java.util.List;

public interface ExtractionService {

    List<Extraction> getExtractions();

    Extraction getExtractionById(Long id);

    Extraction addExtraction(Extraction extraction);

    void deleteExtraction(Extraction extraction);

    void updateExtraction(Long extractionId, Extraction newExtraction);

}
