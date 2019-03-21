package model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "P4_HitData.getByLoginPassword", query = "select hitData from P4_HitData hitData where hitData.user_Id = :user_id"),
        @NamedQuery(name = "P4_HitData.getLastHit", query = "select hitData from P4_HitData hitData where hitData.user_Id = :user_id ORDER BY hitData.id desc")
})
public class P4_HitData {
    @Id
    @GeneratedValue
    private int id;
    private int user_Id;
    private double x;
    private double y;
    private double r;
    private double executionTime;
    private double currentTime;
    private boolean isPointInArea;

    public P4_HitData(double x, double y, double r, double executionTime, double currentTime, int user_Id, boolean isPointInArea) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.executionTime = executionTime;
        this.currentTime = currentTime;
        this.isPointInArea = isPointInArea;
        this.user_Id = user_Id;
    }

    public P4_HitData() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(double executionTime) {
        this.executionTime = executionTime;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    public String getFormattedTime() {
        Date currentDate = new Date((long)currentTime);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        return dateFormat.format(currentDate);
    }

    public boolean isPointInArea() {
        return isPointInArea;
    }

    public void setPointInArea(boolean pointInArea) {
        isPointInArea = pointInArea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }
}