/**
 * @file MobileMainActivity.java
 */
package com.commax.onvif_device_manager;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.commax.onvif_device_manager.content_provider.ContentProviderConstants;
import com.commax.onvif_device_manager.content_provider.ContentProviderManager;
import com.commax.onvif_device_manager.device.DeviceDeleteListener;
import com.commax.onvif_device_manager.device.DeviceInfo;
import com.commax.onvif_device_manager.device.DeviceInfoPopup;
import com.commax.onvif_device_manager.device.IPDevice;
import com.commax.onvif_device_manager.device.IPDeviceListAdapter;
import com.commax.onvif_device_manager.network.NetworkOnvifRequester;
import com.commax.onvif_device_manager.network.OnvifProbe;
import com.commax.onvif_device_manager.uitls.DeviceInfoConfirmListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.onvif.ver10.schema.nativeParcel.ProbeMatch;

import java.util.ArrayList;
import java.util.List;

/**
 * OnVif Discovery로 IP Door Camera와 CCTV 찾는 화면
 */
public class MobileMainActivity extends FragmentActivity implements DeviceInfoConfirmListener, DeviceDeleteListener {


    private IPDevice mIpDevice;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListView();
    }


    /**
     * 스캔한 디바이스를 리스트에 표시
     * @param device
     */
    public void addDeviceToList(final IPDevice device) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ListView deviceList = (ListView) findViewById(R.id.deviceList);
                ((IPDeviceListAdapter) deviceList.getAdapter()).addDevice(device);
            }
        });


    }


    /**
     * ListView 초기화
     */
    private void initListView() {
        List<IPDevice> devices = new ArrayList<IPDevice>();
        ListView deviceList = (ListView) findViewById(R.id.deviceList);
        IPDeviceListAdapter adapter = new IPDeviceListAdapter(this, R.layout.list_item_device, devices);
        deviceList.setAdapter(adapter);

        deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mIpDevice = (IPDevice) parent.getAdapter().getItem(position);

                showPopup();

            }
        });
    }

    /**
     * 아이디, 비밀번호, 디바이스 별명 팝업창 띄움
     */
    private void showPopup() {
        DeviceInfoPopup popup = new DeviceInfoPopup(this, this);
        popup.show();
    }

    /**
     * 디바이스 찾기
     * @param view
     */
    public void findDevice(View view) {
        NetworkOnvifDiscovery discovery = new NetworkOnvifDiscovery();
        discovery.execute();
    }

    /**
     * 사용자가 아이디, 비밀번호, 디바이스 별명을 입력하고 확인버튼을 눌렀을 때 콜백
     * @param deviceInfo
     */
    @Override
    public void onDeviceInfoConfirmed(DeviceInfo deviceInfo) {

        if (isUserIdAndPasswordValid(deviceInfo)) {
            saveDeviceInfoToContentProvider(deviceInfo);
            Toast.makeText(this, "아이디와 비밀번호를 저장했습니다", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "아이디와 비밀번호가 올바르지 않습니다", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 디바이스 정보를 Content Provider에 저장
     * @param deviceInfo
     */
    private void saveDeviceInfoToContentProvider(DeviceInfo deviceInfo) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_IP, mIpDevice.getIpAddress());
        contentValues.put(ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_ID, deviceInfo.getId());
        contentValues.put(ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_PASSWORD, deviceInfo.getPassword());
        contentValues.put(ContentProviderConstants.OnvifDeviceEntry.COLUMN_NAME_DEVICE_NAME, deviceInfo.getDeviceName());

        ContentProviderManager.saveOnvifDevice(this,contentValues);

    }

    /**
     * 사용자 아이디와 비밀번호가 유효한 지 체크
     * @param deviceInfo
     * @return
     */
    private boolean isUserIdAndPasswordValid(DeviceInfo deviceInfo) {

        NetworkOnvifRequester requester = new NetworkOnvifRequester(mIpDevice.getIpAddress(), Integer.parseInt(mIpDevice.getPort()), deviceInfo.getId(), deviceInfo.getPassword());
        int result = requester.createDeviceManagementAuthHeader();
        if (result == NetworkOnvifRequester.ERROR_SOCKET_TIMEOUT) {
            return false;
        }

        if ((result = requester.createMediaManagementAuthHeader()) > -1) {
            if ((result = requester.GetProfiles()) > -1) {
                int profileSize = requester.mGetProfilesResponse.mProfiles.size();
                if (profileSize > 0) {
                    for (int j = 0; j < profileSize; j++) {
                        if ((result = requester.GetStreamUri(requester.mGetProfilesResponse.mProfiles.get(j).mToken)) > -1) {
                            try {
                                requester.mGetStreamUriResponses.get(j).mMediaUri.mUri = requester.mGetStreamUriResponses.get(j).mMediaUri.mUri
                                        .replace("127.0.0.1", mIpDevice.getIpAddress());

                                return true;
                            } catch (Exception e) {
                                break;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    /**
     * 리스트에서 OnVif 디바이스를 삭제한 경우
     */
    @Override
    public void onDelete() {
        //필요한 경우 추가 처리!!

    }

    /**
     * OnVif Discovery 실행
     */
    public class NetworkOnvifDiscovery extends AsyncTask<Void, Void, Integer> {

        private ProgressDialog mProgressDialog;

        public NetworkOnvifDiscovery() {

        }

        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(MobileMainActivity.this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(MobileMainActivity.this.getString(R.string.loading));
            mProgressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            int result = NetworkOnvifRequester.ERROR_UNKNOWN;
            //마지막 파라미터를 null로 주면 모든 IP에 대해 스캔
            OnvifProbe probeIp4 = new OnvifProbe(MobileMainActivity.this, null); //mIPAddress);
            ArrayList<ProbeMatch> probeMatchIp4 = probeIp4.sendProbeMessage(true);
            if (probeMatchIp4 != null && probeMatchIp4.size() > 0) {
                result = NetworkOnvifRequester.SUCCESS;

                for (int i = 0; i < probeMatchIp4.size(); i++) {

                    IPDevice ipDevice = new IPDevice();
                    ipDevice.setIpAddress(probeMatchIp4.get(i).mOnvifIPAddress );
                    ipDevice.setName(probeMatchIp4.get(i).mOnvifVendorModel);
                    ipDevice.setPort(String.valueOf(probeMatchIp4.get(i).mOnvifPort));

                    ipDevice.setId("");
                    ipDevice.setPassword("");

                    addDeviceToList(ipDevice);

                }
            }

            OnvifProbe probeIp6 = new OnvifProbe(MobileMainActivity.this, null);
            ArrayList<ProbeMatch> probeMatchIp6 = probeIp6.sendProbeMessage(false);
            if (probeMatchIp6 != null && probeMatchIp6.size() > 0) {
                ////////////////////////////////////////////////////////////////////////
                //IP 등 변경 테스트
                result = NetworkOnvifRequester.SUCCESS;



                NetworkOnvifRequester requester = null;
                for (int i = 0; i < probeMatchIp6.size(); i++) {
                    requester = new NetworkOnvifRequester(probeMatchIp6.get(i).mOnvifIPAddress, probeMatchIp6.get(i).mOnvifPort, "admin", "12345");
                    if ((result = requester.createDeviceManagementAuthHeader()) > -1) {
                        if ((result = requester.setNetworkInterface()) > -1) {
                           // requester.mSetNetworkResponse.setRebootNeeded(requester.mSetNetworkResponse.mRebootNeeded);
                        }
                    }
                 ////////////////////////////////////////////////////////////////////////


                    IPDevice ipDevice = new IPDevice();
                    ipDevice.setIpAddress(probeMatchIp6.get(i).mOnvifIPAddress );
                    ipDevice.setName(probeMatchIp6.get(i).mOnvifVendorModel);
                    ipDevice.setPort(String.valueOf(probeMatchIp6.get(i).mOnvifPort));

                    ipDevice.setId("");
                    ipDevice.setPassword("");

                    addDeviceToList(ipDevice);

                }
            }




            return result;
        }


        @Override
        protected void onPostExecute(Integer result) {


            if (mProgressDialog != null && mProgressDialog.isShowing())
                mProgressDialog.dismiss();

            super.onPostExecute(result);
        }
    }



}
