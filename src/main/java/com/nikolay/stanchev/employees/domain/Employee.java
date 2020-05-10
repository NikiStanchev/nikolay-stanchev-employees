package com.nikolay.stanchev.employees.domain;

import java.time.LocalDate;

public class Employee {

    private String id;
    private String projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Employee(String id, String projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }
}
