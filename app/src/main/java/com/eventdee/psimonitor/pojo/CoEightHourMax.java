
package com.eventdee.psimonitor.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class CoEightHourMax implements Parcelable {

    @SerializedName("national")
    @Expose
    private Double national;
    @SerializedName("south")
    @Expose
    private Double south;
    @SerializedName("north")
    @Expose
    private Double north;
    @SerializedName("east")
    @Expose
    private Double east;
    @SerializedName("central")
    @Expose
    private Double central;
    @SerializedName("west")
    @Expose
    private Double west;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CoEightHourMax() {
    }

    /**
     * 
     * @param national
     * @param south
     * @param east
     * @param north
     * @param central
     * @param west
     */
    public CoEightHourMax(Double national, Double south, Double north, Double east, Double central, Double west) {
        this.national = national;
        this.south = south;
        this.north = north;
        this.east = east;
        this.central = central;
        this.west = west;
    }

    /**
     * 
     * @return
     *     The national
     */
    public Double getNational() {
        return national;
    }

    /**
     * 
     * @param national
     *     The national
     */
    public void setNational(Double national) {
        this.national = national;
    }

    /**
     * 
     * @return
     *     The south
     */
    public Double getSouth() {
        return south;
    }

    /**
     * 
     * @param south
     *     The south
     */
    public void setSouth(Double south) {
        this.south = south;
    }

    /**
     * 
     * @return
     *     The north
     */
    public Double getNorth() {
        return north;
    }

    /**
     * 
     * @param north
     *     The north
     */
    public void setNorth(Double north) {
        this.north = north;
    }

    /**
     * 
     * @return
     *     The east
     */
    public Double getEast() {
        return east;
    }

    /**
     * 
     * @param east
     *     The east
     */
    public void setEast(Double east) {
        this.east = east;
    }

    /**
     * 
     * @return
     *     The central
     */
    public Double getCentral() {
        return central;
    }

    /**
     * 
     * @param central
     *     The central
     */
    public void setCentral(Double central) {
        this.central = central;
    }

    /**
     * 
     * @return
     *     The west
     */
    public Double getWest() {
        return west;
    }

    /**
     * 
     * @param west
     *     The west
     */
    public void setWest(Double west) {
        this.west = west;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.national);
        dest.writeValue(this.south);
        dest.writeValue(this.north);
        dest.writeValue(this.east);
        dest.writeValue(this.central);
        dest.writeValue(this.west);
    }

    protected CoEightHourMax(Parcel in) {
        this.national = (Double) in.readValue(Double.class.getClassLoader());
        this.south = (Double) in.readValue(Double.class.getClassLoader());
        this.north = (Double) in.readValue(Double.class.getClassLoader());
        this.east = (Double) in.readValue(Double.class.getClassLoader());
        this.central = (Double) in.readValue(Double.class.getClassLoader());
        this.west = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<CoEightHourMax> CREATOR = new Parcelable.Creator<CoEightHourMax>() {
        @Override
        public CoEightHourMax createFromParcel(Parcel source) {
            return new CoEightHourMax(source);
        }

        @Override
        public CoEightHourMax[] newArray(int size) {
            return new CoEightHourMax[size];
        }
    };
}
