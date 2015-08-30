package com.hy.controller;

import com.hy.jdbc.DateSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.okvoice.tts.HanEngine;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class HelloController {
	@RequestMapping(value = "get",method = RequestMethod.GET)
	public void printWelcome(ModelMap model) {
		System.out.println();
		model.addAttribute("message", "Hello world!");
		System.out.println(System.getProperty("java.home"));
		DateSource dateSource = new DateSource();
		ComboPooledDataSource cpds = dateSource.getDataSource();
		try {
			Connection connection = cpds.getConnection();
			System.out.println(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		HanEngine.speechSynthesis("Hello world!");
	}


	/**
	 * 修改语音合成配置
	 * @param speed 语速
	 * @param volume 音量
	 * @param timbre 音色
	 */
	@RequestMapping(value = "speechConfig",method = RequestMethod.POST)
	public void modifySpeechConfig(HttpServletRequest request){
		System.out.println(request.getParameter("speed"));

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

	public int findArrayIndex(String[] str, String timber) {
		for (int i = 0, j = str.length; i < j; i++) {
			if (str[i].equals(timber)) {
				return i;
			}
		}
		return 0;
	}
}