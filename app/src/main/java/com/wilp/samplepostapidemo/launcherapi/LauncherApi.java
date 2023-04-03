package com.wilp.samplepostapidemo.launcherapi;

import com.google.gson.annotations.SerializedName;

public class LauncherApi {

    @SerializedName("dashbaordData")
    DashbaordData mDashbaordData;

    @SerializedName("userDetails")
    UserDetails userDetails;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public DashbaordData getmDashbaordData() {
        return mDashbaordData;
    }

}
