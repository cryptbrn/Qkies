package com.example.qkiesproject;


import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public String name, email,phone,divisi;

    public User(){

    }

    public User(String name, String email,String phone, String divisi){
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.divisi = divisi;
    }

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        divisi = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(divisi);
    }
}
