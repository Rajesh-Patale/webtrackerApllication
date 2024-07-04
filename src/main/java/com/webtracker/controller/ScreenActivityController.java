package com.webtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.webtracker.entity.ScreenActivity;
import com.webtracker.service.ScreenActivityService;

@RestController
@RequestMapping("/api/screen-activities")
@CrossOrigin("*")
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

	@GetMapping("/total/{username}")
	public long getTotalScreenTime(@PathVariable String username) {
		return screenActivityService.getTotalScreenTime(username);
	}

}
