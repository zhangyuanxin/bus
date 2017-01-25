package org.uiplus.bus.parser;

import java.util.List;

/**
 * Created by Steven on 17/1/24.
 */

public class BusLine {

    private String line;

    private String origin;

    private String destination;

    private String runningTime;

    private String originStartTime;

    private String originEndTime;

    private String destinationStartTime;

    private String destinationEndTime;

    private String price;

    private String memo;

    private List<String> originStationList;

    private List<String> destinationStationList;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOriginStartTime() {
        return originStartTime;
    }

    public void setOriginStartTime(String originStartTime) {
        this.originStartTime = originStartTime;
    }

    public String getOriginEndTime() {
        return originEndTime;
    }

    public void setOriginEndTime(String originEndTime) {
        this.originEndTime = originEndTime;
    }

    public String getDestinationStartTime() {
        return destinationStartTime;
    }

    public void setDestinationStartTime(String destinationStartTime) {
        this.destinationStartTime = destinationStartTime;
    }

    public String getDestinationEndTime() {
        return destinationEndTime;
    }

    public void setDestinationEndTime(String destinationEndTime) {
        this.destinationEndTime = destinationEndTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<String> getOriginStationList() {
        return originStationList;
    }

    public void setOriginStationList(List<String> originStationList) {
        this.originStationList = originStationList;
    }

    public List<String> getDestinationStationList() {
        return destinationStationList;
    }

    public void setDestinationStationList(List<String> destinationStationList) {
        this.destinationStationList = destinationStationList;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    @Override
    public String toString() {
        return "BusLine{" +
                "line='" + line + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", runningTime='" + runningTime + '\'' +
                ", originStartTime='" + originStartTime + '\'' +
                ", originEndTime='" + originEndTime + '\'' +
                ", destinationStartTime='" + destinationStartTime + '\'' +
                ", destinationEndTime='" + destinationEndTime + '\'' +
                ", price='" + price + '\'' +
                ", memo='" + memo + '\'' +
                ", originStationList=" + originStationList +
                ", destinationStationList=" + destinationStationList +
                '}';
    }
}
