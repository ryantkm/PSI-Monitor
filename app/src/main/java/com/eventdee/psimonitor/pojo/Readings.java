
package com.eventdee.psimonitor.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Readings implements Parcelable {

    @SerializedName("psi_twenty_four_hourly")
    @Expose
    private PsiTwentyFourHourly psiTwentyFourHourly;
    @SerializedName("pm10_twenty_four_hourly")
    @Expose
    private Pm10TwentyFourHourly pm10TwentyFourHourly;
    @SerializedName("pm10_sub_index")
    @Expose
    private Pm10SubIndex pm10SubIndex;
    @SerializedName("pm25_twenty_four_hourly")
    @Expose
    private Pm25TwentyFourHourly pm25TwentyFourHourly;
    @SerializedName("psi_three_hourly")
    @Expose
    private PsiThreeHourly psiThreeHourly;
    @SerializedName("so2_twenty_four_hourly")
    @Expose
    private So2TwentyFourHourly so2TwentyFourHourly;
    @SerializedName("no2_one_hour_max")
    @Expose
    private No2OneHourMax no2OneHourMax;
    @SerializedName("so2_sub_index")
    @Expose
    private So2SubIndex so2SubIndex;
    @SerializedName("o3_sub_index")
    @Expose
    private O3SubIndex o3SubIndex;
    @SerializedName("pm25_sub_index")
    @Expose
    private Pm25SubIndex pm25SubIndex;
    @SerializedName("co_eight_hour_max")
    @Expose
    private CoEightHourMax coEightHourMax;
    @SerializedName("o3_eight_hour_max")
    @Expose
    private O3EightHourMax o3EightHourMax;
    @SerializedName("co_sub_index")
    @Expose
    private CoSubIndex coSubIndex;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Readings() {
    }

    /**
     * 
     * @param pm25SubIndex
     * @param coSubIndex
     * @param pm10TwentyFourHourly
     * @param coEightHourMax
     * @param no2OneHourMax
     * @param o3SubIndex
     * @param psiTwentyFourHourly
     * @param pm10SubIndex
     * @param psiThreeHourly
     * @param pm25TwentyFourHourly
     * @param o3EightHourMax
     * @param so2TwentyFourHourly
     * @param so2SubIndex
     */
    public Readings(PsiTwentyFourHourly psiTwentyFourHourly, Pm10TwentyFourHourly pm10TwentyFourHourly, Pm10SubIndex pm10SubIndex, Pm25TwentyFourHourly pm25TwentyFourHourly, PsiThreeHourly psiThreeHourly, So2TwentyFourHourly so2TwentyFourHourly, No2OneHourMax no2OneHourMax, So2SubIndex so2SubIndex, O3SubIndex o3SubIndex, Pm25SubIndex pm25SubIndex, CoEightHourMax coEightHourMax, O3EightHourMax o3EightHourMax, CoSubIndex coSubIndex) {
        this.psiTwentyFourHourly = psiTwentyFourHourly;
        this.pm10TwentyFourHourly = pm10TwentyFourHourly;
        this.pm10SubIndex = pm10SubIndex;
        this.pm25TwentyFourHourly = pm25TwentyFourHourly;
        this.psiThreeHourly = psiThreeHourly;
        this.so2TwentyFourHourly = so2TwentyFourHourly;
        this.no2OneHourMax = no2OneHourMax;
        this.so2SubIndex = so2SubIndex;
        this.o3SubIndex = o3SubIndex;
        this.pm25SubIndex = pm25SubIndex;
        this.coEightHourMax = coEightHourMax;
        this.o3EightHourMax = o3EightHourMax;
        this.coSubIndex = coSubIndex;
    }

    /**
     * 
     * @return
     *     The psiTwentyFourHourly
     */
    public PsiTwentyFourHourly getPsiTwentyFourHourly() {
        return psiTwentyFourHourly;
    }

    /**
     * 
     * @param psiTwentyFourHourly
     *     The psi_twenty_four_hourly
     */
    public void setPsiTwentyFourHourly(PsiTwentyFourHourly psiTwentyFourHourly) {
        this.psiTwentyFourHourly = psiTwentyFourHourly;
    }

    /**
     * 
     * @return
     *     The pm10TwentyFourHourly
     */
    public Pm10TwentyFourHourly getPm10TwentyFourHourly() {
        return pm10TwentyFourHourly;
    }

    /**
     * 
     * @param pm10TwentyFourHourly
     *     The pm10_twenty_four_hourly
     */
    public void setPm10TwentyFourHourly(Pm10TwentyFourHourly pm10TwentyFourHourly) {
        this.pm10TwentyFourHourly = pm10TwentyFourHourly;
    }

    /**
     * 
     * @return
     *     The pm10SubIndex
     */
    public Pm10SubIndex getPm10SubIndex() {
        return pm10SubIndex;
    }

    /**
     * 
     * @param pm10SubIndex
     *     The pm10_sub_index
     */
    public void setPm10SubIndex(Pm10SubIndex pm10SubIndex) {
        this.pm10SubIndex = pm10SubIndex;
    }

    /**
     * 
     * @return
     *     The pm25TwentyFourHourly
     */
    public Pm25TwentyFourHourly getPm25TwentyFourHourly() {
        return pm25TwentyFourHourly;
    }

    /**
     * 
     * @param pm25TwentyFourHourly
     *     The pm25_twenty_four_hourly
     */
    public void setPm25TwentyFourHourly(Pm25TwentyFourHourly pm25TwentyFourHourly) {
        this.pm25TwentyFourHourly = pm25TwentyFourHourly;
    }

    /**
     * 
     * @return
     *     The psiThreeHourly
     */
    public PsiThreeHourly getPsiThreeHourly() {
        return psiThreeHourly;
    }

    /**
     * 
     * @param psiThreeHourly
     *     The psi_three_hourly
     */
    public void setPsiThreeHourly(PsiThreeHourly psiThreeHourly) {
        this.psiThreeHourly = psiThreeHourly;
    }

    /**
     * 
     * @return
     *     The so2TwentyFourHourly
     */
    public So2TwentyFourHourly getSo2TwentyFourHourly() {
        return so2TwentyFourHourly;
    }

    /**
     * 
     * @param so2TwentyFourHourly
     *     The so2_twenty_four_hourly
     */
    public void setSo2TwentyFourHourly(So2TwentyFourHourly so2TwentyFourHourly) {
        this.so2TwentyFourHourly = so2TwentyFourHourly;
    }

    /**
     * 
     * @return
     *     The no2OneHourMax
     */
    public No2OneHourMax getNo2OneHourMax() {
        return no2OneHourMax;
    }

    /**
     * 
     * @param no2OneHourMax
     *     The no2_one_hour_max
     */
    public void setNo2OneHourMax(No2OneHourMax no2OneHourMax) {
        this.no2OneHourMax = no2OneHourMax;
    }

    /**
     * 
     * @return
     *     The so2SubIndex
     */
    public So2SubIndex getSo2SubIndex() {
        return so2SubIndex;
    }

    /**
     * 
     * @param so2SubIndex
     *     The so2_sub_index
     */
    public void setSo2SubIndex(So2SubIndex so2SubIndex) {
        this.so2SubIndex = so2SubIndex;
    }

    /**
     * 
     * @return
     *     The o3SubIndex
     */
    public O3SubIndex getO3SubIndex() {
        return o3SubIndex;
    }

    /**
     * 
     * @param o3SubIndex
     *     The o3_sub_index
     */
    public void setO3SubIndex(O3SubIndex o3SubIndex) {
        this.o3SubIndex = o3SubIndex;
    }

    /**
     * 
     * @return
     *     The pm25SubIndex
     */
    public Pm25SubIndex getPm25SubIndex() {
        return pm25SubIndex;
    }

    /**
     * 
     * @param pm25SubIndex
     *     The pm25_sub_index
     */
    public void setPm25SubIndex(Pm25SubIndex pm25SubIndex) {
        this.pm25SubIndex = pm25SubIndex;
    }

    /**
     * 
     * @return
     *     The coEightHourMax
     */
    public CoEightHourMax getCoEightHourMax() {
        return coEightHourMax;
    }

    /**
     * 
     * @param coEightHourMax
     *     The co_eight_hour_max
     */
    public void setCoEightHourMax(CoEightHourMax coEightHourMax) {
        this.coEightHourMax = coEightHourMax;
    }

    /**
     * 
     * @return
     *     The o3EightHourMax
     */
    public O3EightHourMax getO3EightHourMax() {
        return o3EightHourMax;
    }

    /**
     * 
     * @param o3EightHourMax
     *     The o3_eight_hour_max
     */
    public void setO3EightHourMax(O3EightHourMax o3EightHourMax) {
        this.o3EightHourMax = o3EightHourMax;
    }

    /**
     * 
     * @return
     *     The coSubIndex
     */
    public CoSubIndex getCoSubIndex() {
        return coSubIndex;
    }

    /**
     * 
     * @param coSubIndex
     *     The co_sub_index
     */
    public void setCoSubIndex(CoSubIndex coSubIndex) {
        this.coSubIndex = coSubIndex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.psiTwentyFourHourly, flags);
        dest.writeParcelable(this.pm10TwentyFourHourly, flags);
        dest.writeParcelable(this.pm10SubIndex, flags);
        dest.writeParcelable(this.pm25TwentyFourHourly, flags);
        dest.writeParcelable(this.psiThreeHourly, flags);
        dest.writeParcelable(this.so2TwentyFourHourly, flags);
        dest.writeParcelable(this.no2OneHourMax, flags);
        dest.writeParcelable(this.so2SubIndex, flags);
        dest.writeParcelable(this.o3SubIndex, flags);
        dest.writeParcelable(this.pm25SubIndex, flags);
        dest.writeParcelable(this.coEightHourMax, flags);
        dest.writeParcelable(this.o3EightHourMax, flags);
        dest.writeParcelable(this.coSubIndex, flags);
    }

    protected Readings(Parcel in) {
        this.psiTwentyFourHourly = in.readParcelable(PsiTwentyFourHourly.class.getClassLoader());
        this.pm10TwentyFourHourly = in.readParcelable(Pm10TwentyFourHourly.class.getClassLoader());
        this.pm10SubIndex = in.readParcelable(Pm10SubIndex.class.getClassLoader());
        this.pm25TwentyFourHourly = in.readParcelable(Pm25TwentyFourHourly.class.getClassLoader());
        this.psiThreeHourly = in.readParcelable(PsiThreeHourly.class.getClassLoader());
        this.so2TwentyFourHourly = in.readParcelable(So2TwentyFourHourly.class.getClassLoader());
        this.no2OneHourMax = in.readParcelable(No2OneHourMax.class.getClassLoader());
        this.so2SubIndex = in.readParcelable(So2SubIndex.class.getClassLoader());
        this.o3SubIndex = in.readParcelable(O3SubIndex.class.getClassLoader());
        this.pm25SubIndex = in.readParcelable(Pm25SubIndex.class.getClassLoader());
        this.coEightHourMax = in.readParcelable(CoEightHourMax.class.getClassLoader());
        this.o3EightHourMax = in.readParcelable(O3EightHourMax.class.getClassLoader());
        this.coSubIndex = in.readParcelable(CoSubIndex.class.getClassLoader());
    }

    public static final Parcelable.Creator<Readings> CREATOR = new Parcelable.Creator<Readings>() {
        @Override
        public Readings createFromParcel(Parcel source) {
            return new Readings(source);
        }

        @Override
        public Readings[] newArray(int size) {
            return new Readings[size];
        }
    };
}
