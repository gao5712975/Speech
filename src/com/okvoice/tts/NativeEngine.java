package com.okvoice.tts;

public class NativeEngine {
    static {
        System.out.println(System.getProperty("user.dir"));
        System.load(System.getProperty("user.dir") + "\\libokvtts.dll");
        System.load(System.getProperty("user.dir") + "\\okvtts4j.dll");
//        System.loadLibrary("libokvtts");
//        System.loadLibrary("okvtts4j");
    }

    public NativeEngine() {

    }

    public native int init();

    public native int unInit();

    public native int getSupportLang(int[] var1);

    public native int setLangMode(int var1);

    public native int getLangMode();

    public native int setSpeed(int var1);

    public native int getSpeed();

    public native int setVolume(int var1);

    public native int getVolume();

    public native int play(String var1);

    public native int stop();
}
