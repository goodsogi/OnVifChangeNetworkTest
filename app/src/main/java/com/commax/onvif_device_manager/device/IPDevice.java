package com.commax.onvif_device_manager.device;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * IP Device 데이터 모델
 * Created by bagjeong-gyu on 2016. 9. 12..
 */
public class IPDevice implements Parcelable{
    private String name; //디바이스 명
    private String port; //포트
    private String id; //아이디
    private String password; //비밀번호
    private String ipAddress; //ip
    private String newIPAddress; //새로 할당받은 ip
    private String mac; //맥어드레스
    private String sipPhoneNo; //SIP 번호

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
    public String getNewIPAddress() {
        return newIPAddress;
    }

    public void setNewIPAddress(String newIPAddress) {
        this.newIPAddress = newIPAddress;
    }

    public String getSipPhoneNo() {
        return sipPhoneNo;
    }

    public void setSipPhoneNo(String sipPhoneNo) {
        this.sipPhoneNo = sipPhoneNo;
    }

    public IPDevice() {
    }

    public IPDevice(Parcel in) {
        readFromParcel(in);
    }

    public IPDevice(String _name, String _ipAddress, String _newIPAddress, String _mac, String _sipPhoneNo, String _port, String _id, String _password) {
        this.name = _name;
        this.ipAddress = _ipAddress;
        this.newIPAddress = _newIPAddress;
        this.mac = _mac;
        this.sipPhoneNo = _sipPhoneNo;
        this.port= _port;
        this.id = _id;
        this.password = _password;

    }

// -------------------------------------------------------------------------
// Getters & Setters section - 각 필드에 대한 get/set 메소드들
// 여기서는 생략했음
// ....
// ....
// -------------------------------------------------------------------------


    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(ipAddress);
        dest.writeString(newIPAddress);
        dest.writeString(mac);
        dest.writeString(sipPhoneNo);
        dest.writeString(port);
        dest.writeString(id);
        dest.writeString(password);

    }

    private void readFromParcel(Parcel in){
        name = in.readString();
        ipAddress = in.readString();
        newIPAddress = in.readString();
        mac = in.readString();
        sipPhoneNo = in.readString();
        port = in.readString();
        id = in.readString();
        password = in.readString();

    }

    public static final Creator CREATOR = new Creator() {
        public IPDevice createFromParcel(Parcel in) {
            return new IPDevice(in);
        }

        public IPDevice[] newArray(int size) {
            return new IPDevice[size];
        }
    };


    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
