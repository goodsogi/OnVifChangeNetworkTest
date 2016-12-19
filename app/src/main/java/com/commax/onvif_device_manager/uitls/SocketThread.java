package com.commax.onvif_device_manager.uitls;

import java.net.Socket;

/**
 * @brief real ip주소를 획득 하기 위한 스래드
 */
public class SocketThread extends Thread {
	private boolean isRun;
	private String realIP;

	public SocketThread() {
		isRun = true;
	}

	public boolean isRun() {
		return isRun;
	}

	public String getRealIP() {
		return realIP;
	}

	@Override
	public void run() {
		try {
			Socket socket = new Socket("www.google.com", 80);
			realIP = socket.getLocalAddress().getHostAddress().toString();
			isRun = false;
		} catch (Exception e) {
			LOG.i("ERROR", e.getMessage());
			e.printStackTrace();
		}
	}
}
