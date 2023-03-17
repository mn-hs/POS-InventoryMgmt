package com.InventoryMgmt.POSInventoryManagement.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Timesheet {
    private int timesheetId;
    private int employeeId;
    private LocalDate hoursDate;
    private LocalTime clockInTime;
    private LocalTime clockOutTime;
    private long totalHours;

    public Timesheet(){}

    public Timesheet(int timesheetId, int employeeId, LocalDate hoursDate, LocalTime clockInTime, LocalTime clockOutTime,
                     int totalHours) {
        this.timesheetId = timesheetId;
        this.employeeId = employeeId;
        this.hoursDate = hoursDate;
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
        this.totalHours = totalHours;
    }

    public int getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(int timesheetId) {
        this.timesheetId = timesheetId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getHoursDate() {
        return hoursDate;
    }

    public void setHoursDate(LocalDate hoursDate) {
        this.hoursDate = hoursDate;
    }

    public LocalTime getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(LocalTime clockInTime) {
        this.clockInTime = clockInTime;
    }

    public LocalTime getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(LocalTime clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public long getTotalHours() {
        Duration duration = Duration.between(clockInTime, clockOutTime);
        return duration.toHours();
    }

    public void setTotalHours(long totalHours) {
        this.totalHours = totalHours;
    }
}
