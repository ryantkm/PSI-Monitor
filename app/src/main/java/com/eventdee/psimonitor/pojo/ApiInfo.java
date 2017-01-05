
package com.eventdee.psimonitor.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class ApiInfo implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ApiInfo() {
    }

    /**
     * 
     * @param status
     */
    public ApiInfo(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
    }

    protected ApiInfo(Parcel in) {
        this.status = in.readString();
    }

    public static final Parcelable.Creator<ApiInfo> CREATOR = new Parcelable.Creator<ApiInfo>() {
        @Override
        public ApiInfo createFromParcel(Parcel source) {
            return new ApiInfo(source);
        }

        @Override
        public ApiInfo[] newArray(int size) {
            return new ApiInfo[size];
        }
    };
}
