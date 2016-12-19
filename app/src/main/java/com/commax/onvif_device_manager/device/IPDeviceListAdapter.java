package com.commax.onvif_device_manager.device;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.commax.onvif_device_manager.DeviceManagerConstants;
import com.commax.onvif_device_manager.R;
import com.commax.onvif_device_manager.uitls.PlusViewHolder;

import java.util.List;

/**
 * 디바이스 리스트 어댑터
 * Created by bagjeong-gyu on 2016. 8. 22..
 */
public class IPDeviceListAdapter extends ArrayAdapter<IPDevice> {


    private final LayoutInflater mLayoutInflater;
    private final List<IPDevice> mDatas;
    private final Context mContext;
    private DeviceDeleteListener mListener;

    public IPDeviceListAdapter(Context context, int resource, List<IPDevice> devices) {
        super(context, resource, devices);
        mLayoutInflater = LayoutInflater.from(context);
        mDatas = devices;
        mContext = context;
        try {
            mListener = (DeviceDeleteListener) context;
        } catch (ClassCastException e) {
            Log.d(DeviceManagerConstants.LOG_TAG, "ClassCastException: " + e.getMessage());
        }


    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_device,
                    parent, false);
        }


        TextView deviceName = PlusViewHolder.get(convertView, R.id.deviceName);

        String deviceNameString = mDatas.get(position).getName();
        deviceName.setText(deviceNameString);


        TextView ipAddress = PlusViewHolder.get(convertView, R.id.ipAddress);

        String ipAddressString = mDatas.get(position).getIpAddress();
        ipAddress.setText(ipAddressString);


        //디바이스 삭제 버튼
        Button cancelDevice = PlusViewHolder.get(convertView, R.id.cancelDevice);
        cancelDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatas.remove(position);
                resetDatas();
                notifyDataSetChanged();
                mListener.onDelete();
            }
        });

        return convertView;
    }

    /**
     * 디바이스가 삭제된 경우 데이터 리셋
     */
    private void resetDatas() {
//        int wallpadCount = 0;
//        int ipDoorCameraCount = 0;
//        int deviceCount = 1;
//
//        String deviceName = null;
//        String sipPhoneNo = null;
//        String newIP = null;
//
//        IPDevice device = null;
//        int listSize = mDatas.size();
//        for(int i=0; i<listSize; i++) {
//            device = mDatas.get(i);
//            //디바이스 명과 sip 전화번호를 수정
//            if(device.getName().contains(SLAVE_WALLPAD_NAME)) {
//                deviceName = SLAVE_WALLPAD_NAME + " 0" + wallpadCount;
//                //501,502,503,504...로 지정
//                sipPhoneNo = String.valueOf(500 + wallpadCount);
//
//
//
//            } else {
//                deviceName = IP_DOOR_CAMERA_NAME + " 0" + ipDoorCameraCount;
//                //201,202,203,204...로 지정
//                sipPhoneNo = String.valueOf(200 + ipDoorCameraCount);
//            }
//
//            newIP = NewIPAddressManager.getIP(mContext,deviceCount);
//
//            device.setName(deviceName);
//            device.setSipPhoneNo(sipPhoneNo);
//            device.setNewIPAddress(newIP);
//        }
    }


    /**
     * Device 추가
     *
     * @param device
     */
    public void addDevice(IPDevice device) {
        mDatas.add(device);
        notifyDataSetChanged();
    }
}
