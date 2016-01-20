package com.youmu.wheelpicker.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by youzehong on 15/12/27.
 */
public class ProvinceCity implements Parcelable {

    private String province;
    private List<String> citys;

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCitys(List<String> citys) {
        this.citys = citys;
    }

    public String getProvince() {
        return province;
    }

    public List<String> getCitys() {
        return citys;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.province);
        dest.writeStringList(this.citys);
    }

    public ProvinceCity() {
    }

    protected ProvinceCity(Parcel in) {
        this.province = in.readString();
        this.citys = in.createStringArrayList();
    }

    public static final Creator<ProvinceCity> CREATOR = new Creator<ProvinceCity>() {
        public ProvinceCity createFromParcel(Parcel source) {
            return new ProvinceCity(source);
        }

        public ProvinceCity[] newArray(int size) {
            return new ProvinceCity[size];
        }
    };
}
