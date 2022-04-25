package com.example.hibernateTest.repository;

import org.springframework.stereotype.Repository;

import com.example.hibernateTest.dto.Student;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

}
