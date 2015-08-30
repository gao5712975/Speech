package com.okvoice.tts;

public class TTSEngine {
    private static final String[] langModeDesc = new String[]{"普通话男声", "普通话女声", "粤语男声", "粤语女声", "英语男声", "英语女声", "中英混合男", "中英混合女声", "粤英混合男声", "粤英混合声"};
    private static NativeEngine engine = null;

    public TTSEngine() {
        if (engine == null) {
            engine = new NativeEngine();
        }
        this.init();
    }

    public int init() {
        return this.engine.init();
    }

    public int unInit() {
        return this.engine.unInit();
    }

    public int[] getSupportLang() {
        int[] tmp = new int[10];
        int count = this.engine.getSupportLang(tmp);
        if (count < 1) {
            Object var5 = null;
            return null;
        } else {
            int[] lang_mode = new int[count];

            for (int i = 0; i < count; ++i) {
                lang_mode[i] = tmp[i];
            }

            return lang_mode;
        }
    }

    public int setLangMode(int langMode) {
        return engine.setLangMode(langMode);
    }

    public int getLangMode() {
        return engine.getLangMode();
    }

    public String getLangDesc(int langMode) {
        return langMode >= 0 && langMode < 10 ? langModeDesc[langMode] : "不支持的语言模式！";
    }

    public int setSpeed(int speed) {
        return engine.setSpeed(speed);
    }

    public int getSpeed() {
        return engine.getSpeed();
    }

    public int setVolume(int volume) {
        return engine.setVolume(volume);
    }

    public int getVolume() {
        return engine.getVolume();
    }

    public int play(String text) {
        return engine.play(text);
    }

    public int stop() {
        return engine.stop();
    }

//    public static void main(String[] args) {
//        String text = "欢迎使用时代瑞郎语音合成系统，Welcome to use text to speech system of okvoice!";
//        String chs_text = "欢迎使用时代瑞郎语音合成系统。";
//        String eng_text = "Welcome to use text to speech system of okvoice!";
//        TTSEngine engine = new TTSEngine();
//        int result = engine.init();
//        if (result != 0) {
//            System.out.println("ERROR:can not initialize the system!\n");
//        } else {
//            int[] lang_mode = engine.getSupportLang();
//
//            System.out.println(engine.getSpeed());
//            System.out.println(engine.getLangMode());
//            System.out.println(engine.getVolume());
//            engine.setVolume(10);
//            System.out.println(engine.getLangDesc(lang_mode[0]));
//            for (int i = 0; i < lang_mode.length; ++i) {
//                if (engine.setLangMode(lang_mode[i]) == 0) {
//                    switch (lang_mode[i]) {
//                        case 0:
//                        case 1:
//                        case 2:
//                        case 3:
//                            engine.play(chs_text);
//                            break;
//                        case 4:
//                        case 5:
//                            engine.play(eng_text);
//                            break;
//                        case 6:
//                        case 7:
//                        case 8:
//                        case 9:
//                            engine.play(text);
//                            break;
//                        default:
//                            assert false;
//                    }
//                }
//            }
//
//            System.out.println("\nSynthesis ok!\n");
//            engine.unInit();
//        }
//    }
}
