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
        
        uiManager = Tools.getService(this, Context.UI_MODE_SERVICE);
        carMode = Tools.getView(this, R.id.carMode);
        nightMode = Tools.getView(this, R.id.nightMode);
        
        boolean enable = initCarMode();
        radiosEnable(enable);
        initNightMode();
        
        nightMode.setOnCheckedChangeListener(this);
        carMode.setOnCheckedChangeListener(this);
    }
        
    private boolean initCarMode()
    {
        boolean enable = 3 == uiManager.getCurrentModeType();
        carMode.setChecked(enable);
        return enable;
    }
    
    private void radioEnable(int id, boolean enable)
    {
        RadioButton radio = Tools.getView(this, id);
        radio.setEnabled(enable);
    }
    
    private void radiosEnable(boolean enable)
    {
        for (int id : Constants.ID_2_MODE.keySet())
        {
            radioEnable(id, enable);            
        }
    }
    
    private void initNightMode()
    {
        int mode = uiManager.getNightMode();
        if (!Constants.MODE_2_ID.containsKey(mode))
        {
            return;
        }
        RadioButton radio = Tools.getView(this, Constants.MODE_2_ID.get(mode));
        radio.setChecked(true);
    }
    
    @Override
    public void onCheckedChanged(RadioGroup view, int id)
    {
        if (!Constants.ID_2_MODE.containsKey(id))
        {
            return;
        }
        uiManager.setNightMode(Constants.ID_2_MODE.get(id));
    }

    @Override
    public void onCheckedChanged(CompoundButton button, boolean checked)
    {
        radiosEnable(checked);
        saveCarMode(checked);
        if (checked)
        {
            uiManager.enableCarMode(0);
        }
        else
        {
            uiManager.disableCarMode(0);
        }
    }
    
    private void saveCarMode(boolean enable)
    {
        SharedPreferences preferences = getSharedPreferences(Constants.APP_PARAMS, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit= preferences.edit();

        edit.putBoolean(Constants.CAR_MODE, enable);
        edit.commit();
    }
}
