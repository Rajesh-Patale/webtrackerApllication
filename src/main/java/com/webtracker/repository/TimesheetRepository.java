package com.webtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webtracker.entity.Timesheet;


@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long>  {

}
