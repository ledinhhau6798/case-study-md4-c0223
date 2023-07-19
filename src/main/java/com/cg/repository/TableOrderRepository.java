package com.cg.repository;

import com.cg.model.TableOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableOrderRepository extends JpaRepository<TableOrder,Long> {
}
