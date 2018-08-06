package gr.komic.arnold.Models;

public class Progress {
    private String dateAddedString = "";
    private float wristDiameter = 0;
    private float neckDiameter = 0;
    private float waistDiameter = 0;

    public Progress(String dateAddedString, float wristDiameter, float neckDiameter, float waistDiameter) {
        this.dateAddedString = dateAddedString;
        this.wristDiameter = wristDiameter;
        this.neckDiameter = neckDiameter;
        this.waistDiameter = waistDiameter;
    }

    public String getDateAddedString() {
        return dateAddedString;
    }

    public void setDateAddedString(String dateAddedString) {
        this.dateAddedString = dateAddedString;
    }

    public float getWristDiameter() {
        return wristDiameter;
    }

    public void setWristDiameter(float wristDiameter) {
        this.wristDiameter = wristDiameter;
    }

    public float getNeckDiameter() {
        return neckDiameter;
    }

    public void setNeckDiameter(float neckDiameter) {
        this.neckDiameter = neckDiameter;
    }

    public float getWaistDiameter() {
        return waistDiameter;
    }

    public void setWaistDiameter(float waistDiameter) {
        this.waistDiameter = waistDiameter;
    }
}
