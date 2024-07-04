package com.webtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.webtracker.entity.Timesheet;
import com.webtracker.service.TimesheetService;

@RestController
@RequestMapping("/api/timesheets")
@CrossOrigin("*")
public class TimesheetController {
	
	

    @Autowired
    private TimesheetService timesheetService;

    @GetMapping
    public List<Timesheet> getAllTimesheets() {
        return timesheetService.getAllTimesheets();
    }

    @GetMapping("/{id}")
    public Timesheet getTimesheetById(@PathVariable Long id) {
        return timesheetService.getTimesheetById(id);
    }

    @PostMapping
    public Timesheet createTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetService.createTimesheet(timesheet);
    }

    @PutMapping("/{id}")
    public Timesheet updateTimesheet(@PathVariable Long id, @RequestBody Timesheet timesheetDetails) {
        return timesheetService.updateTimesheet(id, timesheetDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTimesheet(@PathVariable Long id) {
        timesheetService.deleteTimesheet(id);
    }

    @PostMapping("/login/{userId}/{projectId}")
    public Timesheet login(@PathVariable Long userId, @PathVariable Long projectId) {
        return timesheetService.login(userId, projectId);
    }

    @PostMapping("/logout/{timesheetId}")
    public Timesheet logout(@PathVariable Long timesheetId) {
        return timesheetService.logout(timesheetId);
    }

}
