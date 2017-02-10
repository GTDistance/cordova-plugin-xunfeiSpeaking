package com.thomas.xunfeispeaking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Thomas.Wang on 17/2/9.
 */
public class XunfeiSpeaking extends CordovaPlugin{

    private Context context;
    private CallbackContext callbackContext;

    @Override
    protected void pluginInitialize() {
        super.pluginInitialize();
        context = cordova.getActivity();
        SpeechUtility.createUtility(context, SpeechConstant.APPID +"=584e7225");
    }

    private static final int DIALOG_ACTIVIT_CODE = 0;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        this.callbackContext = callbackContext;
        //开始听写
        if (action.equals("startListen")){

            Intent intent = new Intent();
            intent.setClass(context, XunfeiDialogActivity.class);
            cordova.startActivityForResult( this,intent, DIALOG_ACTIVIT_CODE);

            return true;
        }

        //停止听写
        if (action.equals("stopListen")) {
//            stopListen();
            return true;
        }


        return false;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case DIALOG_ACTIVIT_CODE:
                if(resultCode == Activity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    callbackContext.success(bundle.getString("result"));
                }else if (resultCode == Activity.RESULT_CANCELED){
                    Bundle bundle = data.getExtras();
                    callbackContext.error(bundle.getString("result"));
                }
                break;
        }
    }

}
