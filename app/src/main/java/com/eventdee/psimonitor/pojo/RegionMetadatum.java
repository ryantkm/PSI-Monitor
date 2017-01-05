
package com.eventdee.psimonitor.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class RegionMetadatum implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("label_location")
    @Expose
    private LabelLocation labelLocation;

    private int imageLocation;
    private String pollutantValue;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RegionMetadatum() {
    }

    /**
     * 
     * @param name
     * @param labelLocation
     */
    public RegionMetadatum(String name, LabelLocation labelLocation) {
        this.name = name;
        this.labelLocation = labelLocation;
    }

    public RegionMetadatum(String name, LabelLocation labelLocation, int imageLocation, double pollutantValue) {
        this.name = name;
        this.labelLocation = labelLocation;
        this.imageLocation = imageLocation;
        this.pollutantValue = String.valueOf(pollutantValue);
    }

    public String getPollutantValue() {
        return pollutantValue;
    }

    public void setpollutantValue(double pollutantValue) {
        this.pollutantValue = String.valueOf(pollutantValue);
    }

    public void setpollutantValue(int pollutantValue) {
        this.pollutantValue = String.valueOf(pollutantValue);
    }

    public int getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(int imageLocation) {
        this.imageLocation = imageLocation;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The labelLocation
     */
    public LabelLocation getLabelLocation() {
        return labelLocation;
    }

    /**
     * 
     * @param labelLocation
     *     The label_location
     */
    public void setLabelLocation(LabelLocation labelLocation) {
        this.labelLocation = labelLocation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable(this.labelLocation, flags);
    }

    protected RegionMetadatum(Parcel in) {
        this.name = in.readString();
        this.labelLocation = in.readParcelable(LabelLocation.class.getClassLoader());
    }

    public static final Parcelable.Creator<RegionMetadatum> CREATOR = new Parcelable.Creator<RegionMetadatum>() {
        @Override
        public RegionMetadatum createFromParcel(Parcel source) {
            return new RegionMetadatum(source);
        }

        @Override
        public RegionMetadatum[] newArray(int size) {
            return new RegionMetadatum[size];
        }
    };
}
