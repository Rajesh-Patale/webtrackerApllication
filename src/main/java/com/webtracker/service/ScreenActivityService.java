package com.webtracker.service;

import java.time.Duration;
import java.util.List;

import com.webtracker.entity.Timesheet;
import com.webtracker.entity.User;
import com.webtracker.repository.TimesheetRepository;
import com.webtracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtracker.entity.ScreenActivity;
import com.webtracker.repository.ScreenActivityRepository;

@Service
public class ScreenActivityService {
	
	

    @Autowired
    private ScreenActivityRepository screenActivityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimesheetRepository timesheetRepository;

    public List<ScreenActivity> getAllScreenActivities() {
        return screenActivityRepository.findAll();
    }

    public ScreenActivity getScreenActivityById(Long id) {
        return screenActivityRepository.findById(id).orElse(null);
    }

    public ScreenActivity createScreenActivity(ScreenActivity screenActivity) {
        return screenActivityRepository.save(screenActivity);
    }

    public ScreenActivity updateScreenActivity(Long id, ScreenActivity screenActivityDetails) {
        ScreenActivity screenActivity = getScreenActivityById(id);
        if (screenActivity != null) {
            screenActivity.setActivity(screenActivityDetails.getActivity());
            return screenActivityRepository.save(screenActivity);
        }
        return null;
    }

    public void deleteScreenActivity(Long id) {
        screenActivityRepository.deleteById(id);
    }

//    public long getTotalScreenTime(String username) {
//        User user = userRepository.findByUsername(username);
//        List<Timesheet> projectTimes = timesheetRepository.findByUser(user);
//
//        return projectTimes.stream()
//                .mapToLong(pt -> Duration.between(pt.getLoginTime(), pt.getLogoutTime()).toMinutes())
//                .sum();
//    }

}
