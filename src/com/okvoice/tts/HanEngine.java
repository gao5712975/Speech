package com.okvoice.tts;

import java.awt.*;

/**
 * 语音合成全局对象
 * Created by Administrator on 15-8-27.
 */
public class HanEngine {
    private static TTSEngine engine = null;
        private static final String[] langModeDesc = new String[]{"普通话男声", "普通话女声", "粤语男声", "粤语女声", "英语男声", "英语女声", "中英混合男", "中英混合女声", "粤英混合男声", "粤英混合声"};

    private static int timeJob = 0;

    private static List dataList = null;

    public static int getTimeJob() {
        return timeJob;
    }

    public static void setTimeJob(int timeJob) {
        HanEngine.timeJob = timeJob;
    }

    public static TTSEngine getTtsEngine() {
        if (engine == null) {
            engine = new TTSEngine();
        }
        return engine;
    }

    public static String[] getLangModeDesc() {
        return langModeDesc;
    }

    /*语音合成*/
    public static void speechSynthesis(String text){
        int[] lang_mode = getTtsEngine().getSupportLang();
        for (int i = 0; i < lang_mode.length; ++i) {
            if (getTtsEngine().setLangMode(lang_mode[i]) == 0) {
                switch (lang_mode[i]) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        getTtsEngine().play(text);
                        break;
                    case 4:
                    case 5:
                        getTtsEngine().play(text);
                        break;
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        getTtsEngine().play(text);
                        break;
                    default:
                        assert false;
                }
            }
        }
    }
}
