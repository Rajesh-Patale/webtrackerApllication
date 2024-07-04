package com.webtracker.repository;

import com.webtracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webtracker.entity.Timesheet;

import java.util.List;


@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long>  {

    List<Timesheet> findByUser(User user);

}
