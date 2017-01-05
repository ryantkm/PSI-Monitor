
package com.eventdee.psimonitor.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class O3SubIndex implements Parcelable {

    @SerializedName("national")
    @Expose
    private Integer national;
    @SerializedName("south")
    @Expose
    private Integer south;
    @SerializedName("north")
    @Expose
    private Integer north;
    @SerializedName("east")
    @Expose
    private Integer east;
    @SerializedName("central")
    @Expose
    private Integer central;
    @SerializedName("west")
    @Expose
    private Integer west;

    /**
     * No args constructor for use in serialization
     * 
     */
    public O3SubIndex() {
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
    public O3SubIndex(Integer national, Integer south, Integer north, Integer east, Integer central, Integer west) {
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
    public Integer getNational() {
        return national;
    }

    /**
     * 
     * @param national
     *     The national
     */
    public void setNational(Integer national) {
        this.national = national;
    }

    /**
     * 
     * @return
     *     The south
     */
    public Integer getSouth() {
        return south;
    }

    /**
     * 
     * @param south
     *     The south
     */
    public void setSouth(Integer south) {
        this.south = south;
    }

    /**
     * 
     * @return
     *     The north
     */
    public Integer getNorth() {
        return north;
    }

    /**
     * 
     * @param north
     *     The north
     */
    public void setNorth(Integer north) {
        this.north = north;
    }

    /**
     * 
     * @return
     *     The east
     */
    public Integer getEast() {
        return east;
    }

    /**
     * 
     * @param east
     *     The east
     */
    public void setEast(Integer east) {
        this.east = east;
    }

    /**
     * 
     * @return
     *     The central
     */
    public Integer getCentral() {
        return central;
    }

    /**
     * 
     * @param central
     *     The central
     */
    public void setCentral(Integer central) {
        this.central = central;
    }

    /**
     * 
     * @return
     *     The west
     */
    public Integer getWest() {
        return west;
    }

    /**
     * 
     * @param west
     *     The west
     */
    public void setWest(Integer west) {
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

    protected O3SubIndex(Parcel in) {
        this.national = (Integer) in.readValue(Integer.class.getClassLoader());
        this.south = (Integer) in.readValue(Integer.class.getClassLoader());
        this.north = (Integer) in.readValue(Integer.class.getClassLoader());
        this.east = (Integer) in.readValue(Integer.class.getClassLoader());
        this.central = (Integer) in.readValue(Integer.class.getClassLoader());
        this.west = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<O3SubIndex> CREATOR = new Parcelable.Creator<O3SubIndex>() {
        @Override
        public O3SubIndex createFromParcel(Parcel source) {
            return new O3SubIndex(source);
        }

        @Override
        public O3SubIndex[] newArray(int size) {
            return new O3SubIndex[size];
        }
    };
}
