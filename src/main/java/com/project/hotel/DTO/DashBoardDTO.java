package com.project.hotel.DTO;

public class DashBoardDTO 
{
    private long totalOrders;
    private double totalSales;
    private long feedbackCount;

    public DashBoardDTO() {
    }

    public DashBoardDTO(long totalOrders,
                        double totalSales,
                        long feedbackCount) {
        this.totalOrders = totalOrders;
        this.totalSales = totalSales;
        this.feedbackCount = feedbackCount;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public long getFeedbackCount() {
        return feedbackCount;
    }

    public void setFeedbackCount(long feedbackCount) {
        this.feedbackCount = feedbackCount;
    }
}
