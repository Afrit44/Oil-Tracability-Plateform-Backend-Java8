package com.Oil4Med.Oil4Med.Repository;

import com.Oil4Med.Oil4Med.Model.Admin;
import com.Oil4Med.Oil4Med.Model.MillManager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MillManagerRepository extends JpaRepository<MillManager, Long> {

    MillManager findMillManagerByEmail(String email);
}
