package com.cg.service.staff;

import com.cg.model.Staff;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StaffServiceImpl implements IStaffService{
    @Override
    public List<Staff> findAll() {
        return null;
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Staff save(Staff staff) {
        return null;
    }

    @Override
    public void delete(Staff staff) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
