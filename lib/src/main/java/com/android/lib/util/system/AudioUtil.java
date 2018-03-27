package com.android.lib.util.system;

//by summer on 2018-01-02.

import android.content.Context;
import android.media.AudioManager;

import com.android.lib.util.LogUtil;

public class AudioUtil {

    private static AudioUtil instance;

    private AudioUtil (){

    }


    public static void setAudio50(Context context){
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int maxStreamRing = audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
        int curStreamRing = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
        audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,maxStreamRing/2,0);
        LogUtil.E(maxStreamRing+":"+curStreamRing+":"+audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL));
    }

    public static void setAudioJia(Context context){
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int maxStreamRing = audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
        int curStreamRing = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
        if(curStreamRing<maxStreamRing){
            audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,++curStreamRing,0);
        }
    }

    public static void setAudioJian(Context context){
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int maxStreamRing = audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL);
        int curStreamRing = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
        if(curStreamRing>0){
            audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,++curStreamRing,0);
        }
    }



}
