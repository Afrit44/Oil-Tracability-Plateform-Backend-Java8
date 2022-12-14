package com.Oil4Med.Oil4Med.Repository;

import com.Oil4Med.Oil4Med.Model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
}
