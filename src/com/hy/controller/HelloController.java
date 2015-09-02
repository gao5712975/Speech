package com.hy.controller;

<<<<<<< HEAD
import com.alibaba.fastjson.JSON;
import com.hy.model.Speech;
import com.hy.service.BasicService;
=======
import com.alibaba.fastjson.JSONArray;
import com.hy.model.SpeechVO;
>>>>>>> 29e7de20a899e3a3b1ef3e0a0ba44ff797a6d40b
import com.okvoice.tts.TTSEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("admin")
public class HelloController {
    @Autowired
    private BasicService basicService;
    private TTSEngine engine = null;

    public TTSEngine getEngine() {
        if (engine == null) {
            engine = new TTSEngine();
        }
        return engine;
    }

    /**
     * 修改语音合成配置
<<<<<<< HEAD
     * <p>
     * //     * @param speed  语速
     * //     * @param volume 音量
     * //     * @param timbre 音色
=======
     *
//     * @param speed  语速
//     * @param volume 音量
//     * @param timbre 音色
>>>>>>> 29e7de20a899e3a3b1ef3e0a0ba44ff797a6d40b
     */
    @RequestMapping(value = "speechConfig")
    public void modifySpeechConfig(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("speed"));
        try {
            Writer writer = response.getWriter();
            writer.write("ok");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
//		if(speed != null && speed.trim() != ""){
//			HanEngine.getTtsEngine().setSpeed(Integer.valueOf(speed));
//		}
//		if(volume != null && volume.trim() != ""){
//			HanEngine.getTtsEngine().setVolume(Integer.valueOf(volume));
//		}
//		if(timbre != null && timbre.trim() != ""){
//			HanEngine.getTtsEngine().setLangMode(findArrayIndex(HanEngine.getLangModeDesc(),timbre));
//		}
    }

    @RequestMapping(value = "login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("user"));
        TTSEngine engine = new TTSEngine();
        engine.play("测试语音");
        try {
            Writer writer = response.getWriter();
            writer.write("ok");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
    }

    /**
     * 语音合成
     */
    @RequestMapping(value = "speechPlay")
    public void speechPlay(HttpServletRequest request, HttpServletResponse response) {
        Enumeration ss = request.getParameterNames();
<<<<<<< HEAD
//        System.out.println(DateSource.getDataSource());
        getEngine().play("1321321321321321");
//        while (ss.hasMoreElements()) {
//            Object b = ss.nextElement();
=======
//        TTSEngine engine = new TTSEngine();
//        engine.play("13213213213213132132");
        while (ss.hasMoreElements()) {
            Object b = ss.nextElement();
//            JSONArray array = JSONArray.parseArray(b.toString());
            System.out.println(b.toString());
>>>>>>> 29e7de20a899e3a3b1ef3e0a0ba44ff797a6d40b

//        }
    }

    public int findArrayIndex(String[] str, String timber) {
        for (int i = 0, j = str.length; i < j; i++) {
            if (str[i].equals(timber)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 获得班次信息列表
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "findDataList")
    public void findSpeedList(HttpServletRequest request, HttpServletResponse response) {
        String id = "";
        List<Speech> list = basicService.find(id);
        String json = JSON.toJSONString(list);
        try {
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            Writer writer = response.getWriter();
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}