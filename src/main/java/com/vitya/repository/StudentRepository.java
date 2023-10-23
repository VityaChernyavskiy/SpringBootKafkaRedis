package com.vitya.repository;

import com.vitya.model.dao.StudentDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentDao, String> {}