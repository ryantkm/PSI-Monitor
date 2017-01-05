
package com.eventdee.psimonitor.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class AirQuality implements Parcelable {

    @SerializedName("api_info")
    @Expose
    private ApiInfo apiInfo;
    @SerializedName("region_metadata")
    @Expose
    private List<RegionMetadatum> regionMetadata = new ArrayList<RegionMetadatum>();
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AirQuality() {
    }

    /**
     * 
     * @param regionMetadata
     * @param items
     * @param apiInfo
     */
    public AirQuality(ApiInfo apiInfo, List<RegionMetadatum> regionMetadata, List<Item> items) {
        this.apiInfo = apiInfo;
        this.regionMetadata = regionMetadata;
        this.items = items;
    }

    /**
     * 
     * @return
     *     The apiInfo
     */
    public ApiInfo getApiInfo() {
        return apiInfo;
    }

    /**
     * 
     * @param apiInfo
     *     The api_info
     */
    public void setApiInfo(ApiInfo apiInfo) {
        this.apiInfo = apiInfo;
    }

    /**
     * 
     * @return
     *     The regionMetadata
     */
    public List<RegionMetadatum> getRegionMetadata() {
        return regionMetadata;
    }

    /**
     * 
     * @param regionMetadata
     *     The region_metadata
     */
    public void setRegionMetadata(List<RegionMetadatum> regionMetadata) {
        this.regionMetadata = regionMetadata;
    }

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.apiInfo, flags);
        dest.writeList(this.regionMetadata);
        dest.writeList(this.items);
    }

    protected AirQuality(Parcel in) {
        this.apiInfo = in.readParcelable(ApiInfo.class.getClassLoader());
        this.regionMetadata = new ArrayList<RegionMetadatum>();
        in.readList(this.regionMetadata, RegionMetadatum.class.getClassLoader());
        this.items = new ArrayList<Item>();
        in.readList(this.items, Item.class.getClassLoader());
    }

    public static final Parcelable.Creator<AirQuality> CREATOR = new Parcelable.Creator<AirQuality>() {
        @Override
        public AirQuality createFromParcel(Parcel source) {
            return new AirQuality(source);
        }

        @Override
        public AirQuality[] newArray(int size) {
            return new AirQuality[size];
        }
    };
}
