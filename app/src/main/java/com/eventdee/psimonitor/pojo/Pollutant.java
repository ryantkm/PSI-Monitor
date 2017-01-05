package com.eventdee.psimonitor.pojo;

public class Pollutant {

    private double value;
    private String unit;
    private String label;

    public Pollutant(double value, String unit, String label) {
        this.value = value;
        this.unit = unit;
        this.label = label;
    }

    public Pollutant(double value, String label) {

        this.value = value;
        this.label = label;
    }

    public Pollutant() {

    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
