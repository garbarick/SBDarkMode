package ru.net.serbis.darkmode;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;

public class Main extends Activity implements
RadioGroup.OnCheckedChangeListener,
CompoundButton.OnCheckedChangeListener
{
    UiModeManager uiManager;
    Switch carMode;
    RadioGroup nightMode;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        uiManager = getService(Context.UI_MODE_SERVICE);
        carMode = getView(R.id.carMode);
        nightMode = getView(R.id.nightMode);
        
        boolean enable = initCarMode();
        radiosEnable(enable);
        initNightMode();
        
        nightMode.setOnCheckedChangeListener(this);
        carMode.setOnCheckedChangeListener(this);
    }
    
    private <T> T getView(int id)
    {
        return (T) findViewById(id);
    }

    private <T> T getService(String id)
    {
        return (T) getSystemService(id);
    }
    
    private boolean initCarMode()
    {
        boolean enable = 3 == uiManager.getCurrentModeType();
        carMode.setChecked(enable);
        return enable;
    }
    
    private void radioEnable(int id, boolean enable)
    {
        RadioButton radio = getView(id);
        radio.setEnabled(enable);
    }
    
    private void radiosEnable(boolean enable)
    {
        radioEnable(R.id.light, enable);
        radioEnable(R.id.dark, enable);
        radioEnable(R.id.auto, enable);
    }
    
    private void initNightMode()
    {
        int mode = uiManager.getNightMode();
        int id;
        switch(mode)
        {
            case UiModeManager.MODE_NIGHT_NO:
                id = R.id.light;
                break;

            case UiModeManager.MODE_NIGHT_YES:
                id = R.id.dark;
                break;

            case UiModeManager.MODE_NIGHT_AUTO:
                id = R.id.auto;
                break;
 
            default:
                return;
        }
        RadioButton radio = getView(id);
        radio.setChecked(true);
    }
    
    @Override
    public void onCheckedChanged(RadioGroup view, int id)
    {
        switch(id)
        {
            case R.id.light:
                uiManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
                break;

            case R.id.dark:
                uiManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
                break;

            case R.id.auto:
                uiManager.setNightMode(UiModeManager.MODE_NIGHT_AUTO);
                break;
        }
    }
    
    @Override
    public void onCheckedChanged(CompoundButton button, boolean checked)
    {
        radiosEnable(checked);
        if (checked)
        {
            uiManager.enableCarMode(0);
        }
        else
        {
            uiManager.disableCarMode(0);
        }
    }
}
