package com.exam.finalexamportal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.service.ExaminationService;

public interface ExaminationRepository extends MongoRepository<Examination, String>{



}
