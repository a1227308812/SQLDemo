package umeng.mysqllitedemo.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Lizhangfeng on 2016/8/8 0008.
 * Description: 车辆bean（主要用于才创建车辆表）
 */
public class BusBean implements Parcelable {

    public String busid;//车id
    public String busname;//车名
    public String busimgurl;//图片地址
    public String busiprice;//指导价格

    public BusBean() {
    }
    //设置数据
    public BusBean(String busid, String busname,String busimgurl,String busiprice) {
        this.busid = busid;
        this.busname = busname;
        this.busimgurl = busimgurl;
        this.busiprice = busiprice;

    }

    @Override
    public String toString() {
        return "BusBean{" +
                "busid='" + busid + '\'' +
                ", busname='" + busname + '\'' +
                ", busimgurl='" + busimgurl + '\'' +
                ", busiprice='" + busiprice + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.busid);
        dest.writeString(this.busname);
        dest.writeString(this.busimgurl);
        dest.writeString(this.busiprice);
    }

    protected BusBean(Parcel in) {
        this.busid = in.readString();
        this.busname = in.readString();
        this.busimgurl = in.readString();
        this.busiprice = in.readString();
    }

    public static final Creator<BusBean> CREATOR = new Creator<BusBean>() {
        @Override
        public BusBean createFromParcel(Parcel source) {
            return new BusBean(source);
        }

        @Override
        public BusBean[] newArray(int size) {
            return new BusBean[size];
        }
    };
}
