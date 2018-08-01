package com.howen.entity;

import java.util.Date;

public class LogEntity {
    private String operator;

    private String date;

    private String operated;

    private Object changeBefore;

    private Object changeAfter;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperated() {
        return operated;
    }

    public void setOperated(String operated) {
        this.operated = operated;
    }

    public Object getChangeBefore() {
        return changeBefore;
    }

    public void setChangeBefore(Object changeBefore) {
        this.changeBefore = changeBefore;
    }

    public Object getChangeAfter() {
        return changeAfter;
    }

    public void setChangeAfter(Object changeAfter) {
        this.changeAfter = changeAfter;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "operator='" + operator + '\'' +
                ", date='" + date + '\'' +
                ", operated='" + operated + '\'' +
                ", changeBefore=" + changeBefore +
                ", changeAfter=" + changeAfter +
                '}';
    }
}
