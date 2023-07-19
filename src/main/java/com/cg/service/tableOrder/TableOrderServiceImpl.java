package com.cg.service.tableOrder;

import com.cg.model.TableOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TableOrderServiceImpl implements ITableOrderService{
    @Override
    public List<TableOrder> findAll() {
        return null;
    }

    @Override
    public Optional<TableOrder> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public TableOrder save(TableOrder tableOrder) {
        return null;
    }

    @Override
    public void delete(TableOrder tableOrder) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
