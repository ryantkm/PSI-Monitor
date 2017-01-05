
package com.eventdee.psimonitor.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class LabelLocation implements Parcelable {

    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LabelLocation() {
    }

    /**
     * 
     * @param longitude
     * @param latitude
     */
    public LabelLocation(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     *     The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.longitude);
        dest.writeValue(this.latitude);
    }

    protected LabelLocation(Parcel in) {
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<LabelLocation> CREATOR = new Parcelable.Creator<LabelLocation>() {
        @Override
        public LabelLocation createFromParcel(Parcel source) {
            return new LabelLocation(source);
        }

        @Override
        public LabelLocation[] newArray(int size) {
            return new LabelLocation[size];
        }
    };
}
