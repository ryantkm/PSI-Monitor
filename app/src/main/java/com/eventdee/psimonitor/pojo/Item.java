
package com.eventdee.psimonitor.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Item implements Parcelable {

    @SerializedName("update_timestamp")
    @Expose
    private String updateTimestamp;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("readings")
    @Expose
    private Readings readings;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }

    /**
     * 
     * @param timestamp
     * @param updateTimestamp
     * @param readings
     */
    public Item(String updateTimestamp, String timestamp, Readings readings) {
        this.updateTimestamp = updateTimestamp;
        this.timestamp = timestamp;
        this.readings = readings;
    }

    /**
     * 
     * @return
     *     The updateTimestamp
     */
    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    /**
     * 
     * @param updateTimestamp
     *     The update_timestamp
     */
    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    /**
     * 
     * @return
     *     The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * 
     * @param timestamp
     *     The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 
     * @return
     *     The readings
     */
    public Readings getReadings() {
        return readings;
    }

    /**
     * 
     * @param readings
     *     The readings
     */
    public void setReadings(Readings readings) {
        this.readings = readings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.updateTimestamp);
        dest.writeString(this.timestamp);
        dest.writeParcelable(this.readings, flags);
    }

    protected Item(Parcel in) {
        this.updateTimestamp = in.readString();
        this.timestamp = in.readString();
        this.readings = in.readParcelable(Readings.class.getClassLoader());
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
