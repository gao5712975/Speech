package com.hy.controller;

import com.alibaba.fastjson.JSON;
import com.hy.model.Speech;
import com.hy.service.BasicService;
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

    /**
     * 修改语音合成配置
     * <p>
     * //     * @param speed  语速
     * //     * @param volume 音量
     * //     * @param timbre 音色
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
//        System.out.println(DateSource.getDataSource());
        TTSEngine engine = new TTSEngine();
        engine.play("1321321321321321");
//        while (ss.hasMoreElements()) {
//            Object b = ss.nextElement();

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