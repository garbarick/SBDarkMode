package ru.net.serbis.darkmode;

import android.app.*;
import android.content.*;
import android.service.quicksettings.*;
import android.widget.*;

public class QuickButton extends TileService
{
    public void onClick()
    {
        super.onClick();
        
        Context context = getApplicationContext();
        UiModeManager uiManager = Tools.getService(context, Context.UI_MODE_SERVICE);
        int mode = uiManager.getNightMode();
        int newMode;
        switch(mode)
        {
            case UiModeManager.MODE_NIGHT_AUTO:
                newMode = UiModeManager.MODE_NIGHT_NO;
                break;
            case UiModeManager.MODE_NIGHT_NO:
                newMode = UiModeManager.MODE_NIGHT_YES;
                break;
            case UiModeManager.MODE_NIGHT_YES:
                newMode = UiModeManager.MODE_NIGHT_AUTO;
                break;
            default:
                return;
        }
        uiManager.setNightMode(newMode);
        String name = getResources().getString(Constants.MODE_2_ID.get(newMode));
        Toast.makeText(context, name, Toast.LENGTH_LONG).show();
    }
}
