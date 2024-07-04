package com.webtracker.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtracker.entity.Timesheet;
import com.webtracker.repository.TimesheetRepository;

@Service
public class TimesheetService {
	
	 @Autowired
	    private TimesheetRepository timesheetRepository;

	    public List<Timesheet> getAllTimesheets() {
	        return timesheetRepository.findAll();
	    }

	    public Timesheet getTimesheetById(Long id) {
	        return timesheetRepository.findById(id).orElse(null);
	    }

	    public Timesheet createTimesheet(Timesheet timesheet) {
	        return timesheetRepository.save(timesheet);
	    }

	    public Timesheet updateTimesheet(Long id, Timesheet timesheetDetails) {
	        Timesheet timesheet = getTimesheetById(id);
	        if (timesheet != null) {
	            timesheet.setLoginTime(timesheetDetails.getLoginTime());
	            timesheet.setLogoutTime(timesheetDetails.getLogoutTime());
	            return timesheetRepository.save(timesheet);
	        }
	        return null;
	    }

	    public void deleteTimesheet(Long id) {
	        timesheetRepository.deleteById(id);
	    }

	    public Timesheet login(Long userId, Long projectId) {
	        Timesheet timesheet = new Timesheet();
	        timesheet.setLoginTime(LocalDateTime.now());
	        // Set user and project
	        // timesheet.setUser(userRepository.findById(userId).orElse(null));
	        // timesheet.setProject(projectRepository.findById(projectId).orElse(null));
	        return timesheetRepository.save(timesheet);
	    }

	    public Timesheet logout(Long timesheetId) {
	        Timesheet timesheet = getTimesheetById(timesheetId);
	        if (timesheet != null) {
	            timesheet.setLogoutTime(LocalDateTime.now());
	            return timesheetRepository.save(timesheet);
	        }
	        return null;
	    }

}
