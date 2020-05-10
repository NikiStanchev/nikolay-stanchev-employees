package com.nikolay.stanchev.employees.domain;

public class EmployeeCouple {

    private String firstEmployeeId;
    private String secondEmployeeId;
    private String projectId;
    private int daysWorked;

    public EmployeeCouple() {
    }

    public EmployeeCouple(String firstEmployeeId, String secondEmployeeId, String projectId, int daysWorked) {
        this.firstEmployeeId = firstEmployeeId;
        this.secondEmployeeId = secondEmployeeId;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public String getFirstEmployeeId() {
        return firstEmployeeId;
    }

    public void setFirstEmployeeId(String firstEmployeeId) {
        this.firstEmployeeId = firstEmployeeId;
    }

    public String getSecondEmployeeId() {
        return secondEmployeeId;
    }

    public void setSecondEmployeeId(String secondEmployeeId) {
        this.secondEmployeeId = secondEmployeeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }
}