package com.hy.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hy.model.SpeechBus;
import com.hy.service.BasicService;
import com.okvoice.tts.TTSEngine;
import com.util.ApplicationContextHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin")
public class SpeechsController {
    private static final String[] langModeDesc = new String[]{"��ͨ������", "��ͨ��Ů��", "��������", "����Ů��", "Ӣ������", "Ӣ��Ů��", "��Ӣ�����", "��Ӣ���Ů��", "��Ӣ�������", "��Ӣ�����"};
    @Autowired
    private BasicService basicService;
    private TTSEngine engine = null;
    private TTSEngine engineProxy = null;

    public SpeechsController() {
    }

    public TTSEngine getEngine() {
        if(this.engine == null) {
            this.engine = (TTSEngine) ApplicationContextHelper.getBean("engine");
        }

        return this.engine;
    }

    @RequestMapping({"speechConfig"})
    public void modifySpeechConfig(@RequestParam("speed") String speed, @RequestParam("volume") String volume, @RequestParam("timbre") String timbre, HttpServletResponse response) {
        if(speed != null && "".equals(speed.trim())) {
            this.getEngine().setSpeed(Integer.valueOf(speed).intValue());
        }

        if(volume != null && "".equals(volume.trim())) {
            this.getEngine().setVolume(Integer.valueOf(volume).intValue());
        }

        if(timbre != null && "".equals(timbre.trim())) {
            this.getEngine().setLangMode(this.findArrayIndex(langModeDesc, timbre));
        }

        try {
            PrintWriter e = response.getWriter();
            e.write("ok");
            e.flush();
            e.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    @RequestMapping({"login"})
    public void login(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter e = response.getWriter();
            e.write("ok");
            e.flush();
            e.close();
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }

    @RequestMapping("speechPlay")
    public void speechPlay(HttpServletRequest request, HttpServletResponse response, @RequestParam("speech") String speech) {
        try {
            if(this.engineProxy == null) {
                this.engineProxy = this.getEngine();
            }
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            PrintWriter e = response.getWriter();
            JSONObject result = new JSONObject();
            int a = this.engineProxy.play(speech);
            this.engineProxy = null;
            System.out.println(a);
            result.put("status", Integer.valueOf(a));
            e.write(result.toJSONString());
            e.flush();
            e.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    @RequestMapping("stop")
    public void speechStop() {
        if(this.engineProxy != null) {
            System.out.println(this.engineProxy.stop());
            this.engineProxy = null;
        }

    }

    @RequestMapping("play")
    public void play(@ModelAttribute SpeechBus speechBus, HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("进来了");
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            PrintWriter e = response.getWriter();
            if(this.engineProxy != null) {
                String task = speechBus.getTime() + speechBus.getCarNumber() + speechBus.getTerminus() + speechBus.getCarUnit();

                for(int result = 0; result < Integer.valueOf(speechBus.getTaskNumber()); ++result) {
                    System.out.println(this.engineProxy.play(task));
                }
                JSONObject var8 = new JSONObject();
                var8.put("id", speechBus.getId());
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
        if(this.engineProxy == null) {
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
        try {
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            PrintWriter e = response.getWriter();
            String json;
//            if(id == null && "".equals(id)) {
//                response.setStatus(405);
//                json = "����д�����ȨID";
//            } else {
            List list = this.basicService.find(id);
            json = JSON.toJSONString(list);
            response.setStatus(200);
//            }

            e.write(json);
            e.flush();
            e.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    public int findArrayIndex(String[] str, String timber) {
        int i = 0;

        for(int j = str.length; i < j; ++i) {
            if(str[i].equals(timber)) {
                return i;
            }
        }

        return 0;
    }
}
