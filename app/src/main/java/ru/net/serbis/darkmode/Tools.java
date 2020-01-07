package ru.net.serbis.darkmode;

import android.app.*;
import android.content.*;
import android.os.*;

public class Tools
{
    public static <T> T getView(Activity context, int id)
    {
        return (T) context.findViewById(id);
    }

    public static <T> T getService(Context context, String id)
    {
        return (T) context.getSystemService(id);
    }
    
    public static boolean noNeedCarMod()
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}
