package com.hy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hy.model.SpeechBus;
import com.hy.service.BasicService;
import com.okvoice.tts.TTSEngine;
import com.util.ApplicationContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("admin")
public class SpeechsController {
    private static final String[] langModeDesc = new String[]{"普通话男声", "普通话女声", "粤语男声", "粤语女声", "英语男声", "英语女声", "中英混合男", "中英混合女声", "粤英混合男声", "粤英混合声"};
    @Autowired
    private BasicService basicService;
    private TTSEngine engine = null;
    private TTSEngine engineProxy = null;
    private int status = -1; //0开始读，1插播被停止，播报被停止，2插播状态，播报等待，

    public SpeechsController() {
    }

    public TTSEngine getEngine() {
        if (this.engine == null) {
            this.engine = (TTSEngine) ApplicationContextHelper.getBean("engine");
        }

        return this.engine;
    }

    @RequestMapping({"speechConfig"})
    public void modifySpeechConfig(@RequestParam("speed") String speed, @RequestParam("volume") String volume, @RequestParam("timbre") String timbre, HttpServletResponse response) {
        if (speed != null && "".equals(speed.trim())) {
            this.getEngine().setSpeed(Integer.valueOf(speed).intValue());
        }

        if (volume != null && "".equals(volume.trim())) {
            this.getEngine().setVolume(Integer.valueOf(volume).intValue());
        }

        if (timbre != null && "".equals(timbre.trim())) {
            this.getEngine().setLangMode(this.findArrayIndex(langModeDesc, timbre));
        }

        try {
            PrintWriter e = response.getWriter();
            e.write("01");
            e.flush();
            e.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    @RequestMapping("login")
    public void login(HttpServletRequest request, HttpServletResponse response, @RequestParam("user") String user, @RequestParam("password") String password) {
        PrintWriter pw;
        JSONObject result = new JSONObject();
        try {
            String id = basicService.login(user, password);
            System.out.println(id);
            pw = response.getWriter();
            if ("".equals(id)) {
                result.put("status", "00");
                pw.write(JSON.toJSONString(result));
                pw.flush();
                pw.close();
            } else {
                result.put("status", "01");
                result.put("id", id);
                pw.write(JSON.toJSONString(result));
                pw.flush();
                pw.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            try {
                pw = response.getWriter();
                result.put("status", "02");
                pw.write(JSON.toJSONString(result));
                pw.flush();
                pw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    @RequestMapping("speechPlay")
    public void speechPlay(HttpServletRequest request, HttpServletResponse response, @RequestParam("speech") String speech) {
        System.out.println(speech);
        try {
            if (this.engineProxy == null) {
                this.engineProxy = this.getEngine();
            }
            //播放状态
            if (status == -1) {
                status = 1;
            }
            if (status == 0) {
                status = 2;
            }
            this.engineProxy.stop();
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            PrintWriter e = response.getWriter();
            JSONObject result = new JSONObject();
            int a;
            System.out.println(isMessyCode(speech));
            if (isMessyCode(speech)) {
                a = this.engineProxy.play("输入有误");
            } else {
                a = this.engineProxy.play(speech);
            }
            System.out.println(a);
            result.put("status", status);
            e.write(result.toJSONString());
            e.flush();
            e.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }

    @RequestMapping("stop")
    public void stop(HttpServletResponse response) {
        try {
            PrintWriter e = response.getWriter();
            if (this.engineProxy != null) {
                System.out.println(this.engineProxy.stop());
                status = -1;
                this.engineProxy = null;
                e.write("任务结束");
                e.flush();
                e.close();
            } else {
                e.write("没有任务");
                e.flush();
                e.close();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    @RequestMapping("play")
    public void play(@ModelAttribute SpeechBus speechBus, HttpServletResponse response) {
        try {
            status = 0;
            System.out.println("开始");
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            PrintWriter e = response.getWriter();
            if (this.engineProxy != null) {
                for (int result = 0; result < Integer.valueOf(speechBus.getTaskNumber()); ++result) {
                    if (engineProxy != null) {
                        this.engineProxy.play(speechBus.getRulePlay());
                    } else {
                        break;
                    }
                }
                JSONObject var8 = new JSONObject();
                System.out.println("完毕");
                var8.put("id", speechBus.getId());
                var8.put("status", status);
                e.write(var8.toJSONString());
                e.flush();
                e.close();
            } else {
                response.setStatus(500);
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    @RequestMapping("initEngineProxy")
    public void initEngineProxy(@RequestParam("type") String type, HttpServletResponse response) {
        if (this.engineProxy == null) {
            this.engineProxy = this.getEngine();
        }
        try {
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            PrintWriter e = response.getWriter();
            JSONObject result = new JSONObject();
            result.put("type", type);
            e.write(result.toJSONString());
            e.flush();
            e.close();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    @RequestMapping({"findDataList"})
    public void findSpeedList(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id) {
        PrintWriter pw;
        JSONObject result = new JSONObject();
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        try {
            pw = response.getWriter();
            System.out.println("id:" + id);
            String json;
            if (id == null || "".equals(id)) {
                result.put("status", "00");//没有登录，或者用户没有车站信息
                pw.write(JSON.toJSONString(result));
                pw.flush();
                pw.close();
            } else {
                List list = this.basicService.find(id);
                json = JSON.toJSONString(list);
                result.put("status", "01");//正确结果
                result.put("result", json);
                System.out.println("有数据");
                pw.write(JSON.toJSONString(result));
                pw.flush();
                pw.close();
            }
        } catch (Exception e) {
            try {
                pw = response.getWriter();
                result.put("status", "02");//系统错误
                pw.write(JSON.toJSONString(result));
                pw.flush();
                pw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public int findArrayIndex(String[] str, String timber) {
        int i = 0;
        for (int j = str.length; i < j; ++i) {
            if (str[i].equals(timber)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 判断是否为乱码
     *
     * @param str
     * @return
     */
    public static boolean isMessyCode(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 当从Unicode编码向某个字符集转换时，如果在该字符集中没有对应的编码，则得到0x3f（即问号字符?）
            //从其他字符集向Unicode编码转换时，如果这个二进制数在该字符集中没有标识任何的字符，则得到的结果是0xfffd
            //System.out.println("--- " + (int) c);
            if ((int) c == 0xfffd) {
                return true;
            }
        }
        return false;
    }
}
