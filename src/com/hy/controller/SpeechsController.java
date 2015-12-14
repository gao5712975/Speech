package com.hy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hy.model.Speech;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class SpeechsController {
    private static final String[] langModeDesc = new String[]{"普通话男声", "普通话女声", "粤语男声", "粤语女声", "英语男声", "英语女声", "中英混合男", "中英混合女声", "粤英混合男声", "粤英混合声"};
    @Autowired
    private BasicService basicService;
    private TTSEngine engineProxy = null;
    private int status = 1; //0开始读，1插播被停止，播报被停止，2插播状态，播报等待，

    public SpeechsController() {
    }

    public TTSEngine getEngine() {
        if (this.engineProxy == null) {
            this.engineProxy = (TTSEngine) ApplicationContextHelper.getBean("engine");
        }
        return this.engineProxy;
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
            this.getEngine().setLangMode(Integer.valueOf(timbre).intValue());
        }
        try {
            PrintWriter e = response.getWriter();
            e.write("0");
            e.flush();
            e.close();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }

    @RequestMapping("login")
    public void login(HttpServletResponse response, @RequestParam("user") String user, @RequestParam("password") String password) {
        PrintWriter pw = null;
        JSONObject result = new JSONObject();
        try {
            String id = basicService.login(user, password);
            System.out.println(id);
            pw = response.getWriter();
            if ("".equals(id)) {
                result.put("status", 1);
                pw.write(JSON.toJSONString(result));
            } else {
                result.put("status", 0);
                result.put("id", id);
                pw.write(JSON.toJSONString(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                pw = response.getWriter();
                result.put("status", 2);
                pw.write(JSON.toJSONString(result));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            pw.flush();
            pw.close();
        }

    }

    @RequestMapping("stop")
    public void stop(HttpServletResponse response) {
        try {
            status = 3;
            PrintWriter e = response.getWriter();
            JSONObject result = new JSONObject();
            this.getEngine().stop();
            this.engineProxy = null;
            result.put("status", 3);
            e.write(result.toJSONString());
            e.flush();
            e.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private List<TTSEngine> list = new ArrayList<TTSEngine>();
    @RequestMapping("play")
    public void play(@ModelAttribute SpeechBus speechBus, HttpServletResponse response) {
        try {
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            PrintWriter e = response.getWriter();
            if (this.engineProxy == null) {
                this.engineProxy = this.getEngine();
            }
            list.add(engineProxy);
            for (int result = 0; result < Integer.valueOf(speechBus.getTaskNumber()); ++result) {
                if (engineProxy != null) {
                    this.engineProxy.play(speechBus.getRulePlay());
                } else {
                    break;
                }
            }
            if(list.size()>1){
                status = 1;
            }else{
                if(status != 3){//如果点击停止按钮就不把状态改为0；
                    status = 0;
                }
            }
            list.remove(list.size()-1);
            JSONObject var8 = new JSONObject();
            System.out.println("完毕");
            var8.put("id", speechBus.getId());
            var8.put("status", status);
            e.write(var8.toJSONString());
            e.flush();
            e.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }
    }

    @RequestMapping({"findDataList"})
    public void findSpeedList(HttpServletResponse response, @RequestParam("id") String id) {
        PrintWriter pw = null;
        JSONObject result = new JSONObject();
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        try {
            pw = response.getWriter();
            System.out.println("id:" + id);
            if (id == null || "".equals(id)) {
                result.put("status", "1");//没有登录，或者用户没有车站信息
                pw.write(JSON.toJSONString(result));
            } else {
                List<Speech> list = this.basicService.find(id);
                result.put("status", "0");//正确结果
                result.put("result", list);
                System.out.println("有数据!");
                pw.write(JSON.toJSONString(result));
            }
        } catch (Exception e) {
            try {
                pw = response.getWriter();
                result.put("status", "2");//系统错误
                pw.write(JSON.toJSONString(result));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            pw.flush();
            pw.close();
        }
    }
}