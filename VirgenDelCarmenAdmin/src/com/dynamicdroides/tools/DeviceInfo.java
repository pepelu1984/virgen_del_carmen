package com.dynamicdroides.tools;
import org.springframework.mobile.device.Device;

import java.util.Collections;
import java.util.Map;

/**
 * Provides a vendor-independent description of a device.
 */
public class DeviceInfo implements Device {

    public boolean isDisplayNormal() {
		return displayNormal;
	}

	public void setDisplayNormal(boolean displayNormal) {
		this.displayNormal = displayNormal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getMarkUp() {
		return markUp;
	}

	public void setMarkUp(String markUp) {
		this.markUp = markUp;
	}

	public Map getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(Map capabilities) {
		this.capabilities = capabilities;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	private boolean mobile;

    private boolean displayNormal;

    private String id;

    private String userAgent;

    private String markUp;

    private Map capabilities;

    public DeviceInfo(boolean mobile) {
        this.mobile = mobile;
    }

    @Override
    public boolean isMobile() {
        return mobile;
    }

	@Override
	public boolean isNormal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTablet() {
		// TODO Auto-generated method stub
		return false;
	}

    // the usual getters and setters
}