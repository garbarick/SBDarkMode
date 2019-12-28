package ru.net.serbis.darkmode;

public class Log
{
    public static void info(Object o, String message)
    {
        android.util.Log.i(o.getClass().getName(), message);
    }

    public static void info(Object o, String message, Throwable e)
    {
        android.util.Log.i(o.getClass().getName(), message, e);
    }

    public static void info(Object o, Throwable e)
    {
        info(o, "Error", e);
    }
}
