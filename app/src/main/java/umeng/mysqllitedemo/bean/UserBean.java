package umeng.mysqllitedemo.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Lizhangfeng on 2016/8/8 0008.
 * Description: 用户信息bean
 */
public class UserBean implements Parcelable {

    public String userAccount;
    public String userPassword;
    public String userName;
    public String userSex;

    public UserBean() {
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userAccount);
        dest.writeString(this.userPassword);
        dest.writeString(this.userName);
        dest.writeString(this.userSex);
    }

    protected UserBean(Parcel in) {
        this.userAccount = in.readString();
        this.userPassword = in.readString();
        this.userName = in.readString();
        this.userSex = in.readString();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
