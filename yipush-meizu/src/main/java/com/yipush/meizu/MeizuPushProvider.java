package com.yipush.meizu;

import android.content.Context;
import android.os.Build;

import com.meizu.cloud.pushsdk.PushManager;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import com.yipush.core.BaseYiPushProvider;
import com.yipush.core.RegisterType;

public class MeizuPushProvider extends BaseYiPushProvider {
    public static final String MEIZU = "meizu";
    private String appId;
    private String appKey;

    @Override
    public void register(Context context, RegisterType type) {
        appId = getMetaData(context, "MEIZU_APP_ID");
        appKey = getMetaData(context, "MEIZU_APP_KEY");
        PushManager.register(context, appId, appKey);
    }

    @Override
    public void unRegister(Context context) {
        PushManager.unRegister(context, appId, appKey);
    }

    @Override
    public String getRegisterId(Context context) {
        return PushManager.getPushId(context);
    }


    @Override
    public boolean isSupport(Context context) {
        String manufacturer = Build.MANUFACTURER.toLowerCase();
        if (manufacturer.equals("meizu")) {
            return MzSystemUtils.isMeizuAndFlyme();
        }
        return false;
    }

    @Override
    public String getPlatformName() {
        return MeizuPushProvider.MEIZU;
    }
}
