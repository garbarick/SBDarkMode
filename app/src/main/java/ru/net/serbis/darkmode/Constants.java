package ru.net.serbis.darkmode;

import android.app.*;
import java.util.*;

public interface Constants
{
    Map<Integer, Integer> ID_2_MODE = new HashMap<Integer, Integer>()
    {
        {
            put(R.id.light, UiModeManager.MODE_NIGHT_NO);
            put(R.id.dark, UiModeManager.MODE_NIGHT_YES);
            put(R.id.auto, UiModeManager.MODE_NIGHT_AUTO);
        }
    };
    
    Map<Integer, Integer> MODE_2_ID =  new HashMap<Integer, Integer>()
    {
        {
            for(Map.Entry<Integer, Integer> entry : ID_2_MODE.entrySet())
            {
                put(entry.getValue(), entry.getKey());
            }
        }
    };
    
    String APP_PARAMS = "app_params";
    String CAR_MODE = "car_mode";
}
