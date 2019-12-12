package com.company.model;

import net.sf.json.JSONObject;

public class BarChartDTO {

    private JSONObject dataSource;
    private String firstCompany;
    private String firstExchange;
    private String secondCompany;
    private String secondExchange;
    private String fromTime;
    private String toTime;
    private String chartType;

    public String getFirstCompany() {
        return firstCompany;
    }

    public void setFirstCompany(String firstCompany) {
        this.firstCompany = firstCompany;
    }

    public String getFirstExchange() {
        return firstExchange;
    }

    public void setFirstExchange(String firstExchange) {
        this.firstExchange = firstExchange;
    }

    public String getSecondCompany() {
        return secondCompany;
    }

    public void setSecondCompany(String secondCompany) {
        this.secondCompany = secondCompany;
    }

    public String getSecondExchange() {
        return secondExchange;
    }

    public void setSecondExchange(String secondExchange) {
        this.secondExchange = secondExchange;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public JSONObject getDataSource() {
        return dataSource;
    }

    public void setDataSource(JSONObject dataSource) {
        this.dataSource = dataSource;
    }
}
