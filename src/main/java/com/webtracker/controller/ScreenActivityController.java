package com.webtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webtracker.entity.ScreenActivity;
import com.webtracker.service.ScreenActivityService;

@RestController
@RequestMapping("/api/screen-activities")
public class ScreenActivityController {
	
	 @Autowired
	    private ScreenActivityService screenActivityService;

	    @GetMapping
	    public List<ScreenActivity> getAllScreenActivities() {
	        return screenActivityService.getAllScreenActivities();
	    }

	    @GetMapping("/{id}")
	    public ScreenActivity getScreenActivityById(@PathVariable Long id) {
	        return screenActivityService.getScreenActivityById(id);
	    }

	    @PostMapping
	    public ScreenActivity createScreenActivity(@RequestBody ScreenActivity screenActivity) {
	        return screenActivityService.createScreenActivity(screenActivity);
	    }

	    @PutMapping("/{id}")
	    public ScreenActivity updateScreenActivity(@PathVariable Long id, @RequestBody ScreenActivity screenActivityDetails) {
	        return screenActivityService.updateScreenActivity(id, screenActivityDetails);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteScreenActivity(@PathVariable Long id) {
	        screenActivityService.deleteScreenActivity(id);
	    }

}
