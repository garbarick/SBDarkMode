package ru.net.serbis.darkmode;

import android.content.*;
import android.app.*;

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
}
