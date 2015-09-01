package com.hy.controller;

import com.alibaba.fastjson.JSONArray;
import com.hy.model.SpeechVO;
import com.okvoice.tts.TTSEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

@Controller
@RequestMapping("admin")
public class HelloController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "hello";
    }


    /**
     * 修改语音合成配置
     *
//     * @param speed  语速
//     * @param volume 音量
//     * @param timbre 音色
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
    public void speechPlay(HttpServletRequest request, HttpServletResponse response, @ModelAttribute() SpeechVO speechVO) {
//        Map m = request.getParameterMap();
//        String[] d = request.getParameterValues("string");
//        String s = request.getParameter("string");
        Enumeration ss = request.getParameterNames();
//        TTSEngine engine = new TTSEngine();
//        engine.play("13213213213213132132");
        while (ss.hasMoreElements()) {
            Object b = ss.nextElement();
//            JSONArray array = JSONArray.parseArray(b.toString());
            System.out.println(b.toString());

        }
    }

    public int findArrayIndex(String[] str, String timber) {
        for (int i = 0, j = str.length; i < j; i++) {
            if (str[i].equals(timber)) {
                return i;
            }
        }
        return 0;
    }
}