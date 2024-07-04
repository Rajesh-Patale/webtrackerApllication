package com.webtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtracker.entity.ScreenActivity;
import com.webtracker.repository.ScreenActivityRepository;

@Service
public class ScreenActivityService {
	
	

    @Autowired
    private ScreenActivityRepository screenActivityRepository;

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

}
