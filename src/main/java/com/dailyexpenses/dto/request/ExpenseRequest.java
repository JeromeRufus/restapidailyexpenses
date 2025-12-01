package com.dailyexpenses.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRequest {

    private String description;
    private double totalAmount;
    private String splitType;

    private List<String> participants = new ArrayList<>();
    private List<Double> exactAmounts = new ArrayList<>();
    private List<Integer> percentage = new ArrayList<>();

    // getters and setters
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSplitType() {
        return splitType;
    }
    public void setSplitType(String splitType) {
        this.splitType = splitType;
    }

    public List<String> getParticipants() {
        return participants;
    }
    public void setParticipants(List<String> participants) {
        this.participants = participants != null ? participants : new ArrayList<>();
    }

    public List<Double> getExactAmounts() {
        return exactAmounts;
    }
    public void setExactAmounts(List<Double> exactAmounts) {
        this.exactAmounts = exactAmounts != null ? exactAmounts : new ArrayList<>();
    }

    public List<Integer> getPercentage() {
        return percentage;
    }
    public void setPercentage(List<Integer> percentage) {
        this.percentage = percentage != null ? percentage : new ArrayList<>();
    }
}
