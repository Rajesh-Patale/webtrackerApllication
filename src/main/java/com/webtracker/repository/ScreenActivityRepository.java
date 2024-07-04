package com.webtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webtracker.entity.ScreenActivity;


@Repository
public interface ScreenActivityRepository extends JpaRepository <ScreenActivity, Long> {

}
