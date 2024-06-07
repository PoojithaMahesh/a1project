package com.jsp.projecta1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.projecta1.entity.Medicine;

public interface MedicineRepo extends JpaRepository<Medicine, Integer>{

}
