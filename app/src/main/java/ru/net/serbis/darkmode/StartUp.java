package ru.net.serbis.darkmode;

import android.app.*;
import android.content.*;

public class StartUp extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (Tools.noNeedCarMod())
        {
            return;
        }
        UiModeManager uiManager = Tools.getService(context, Context.UI_MODE_SERVICE);
        boolean systemMode = 3 == uiManager.getCurrentModeType();
        if (systemMode)
        {
            return;
        }
        SharedPreferences preferences = context.getSharedPreferences(Constants.APP_PARAMS, Context.MODE_PRIVATE);
        boolean mode = preferences.getBoolean(Constants.CAR_MODE, false);
        if (mode)
        {
            uiManager.enableCarMode(0);
        }
    }
}
