package com.Oil4Med.Oil4Med.Service;


import com.Oil4Med.Oil4Med.Model.MillManager;

import java.util.List;

public interface MillManagerService {

    List<MillManager> getMillManagers();
    MillManager getMillManagerById(Long id);
    MillManager addMillManager(MillManager millManager);
    void deleteMillManager(MillManager millManager);
    void updateMillManager(Long millManagerId, MillManager millManager);
}
