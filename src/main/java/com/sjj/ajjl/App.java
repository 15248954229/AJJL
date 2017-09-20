package com.sjj.ajjl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.println("请输入按键频率（单位：毫秒）：");
		Scanner sc = new Scanner(System.in);
		String content = sc.nextLine();
		System.out.println("输入内容为：" + content + "毫秒");
		String rex = "^\\+?[1-9][0-9]*$";// 正整数判断。
		Pattern p = Pattern.compile(rex);
		Matcher m = p.matcher(content);
		int ms = 500;
		if (m.find()) {
			try {
				ms = Integer.parseInt(content);
			} catch (Exception e) {
				System.out.println("类型转换错误，默认以500毫秒频率进行按键");
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("输入不合法，默认以500毫秒频率进行按键");
		}

		Robot robot;
		try {
			robot = new Robot();
			while (true) {
				robot.keyPress(KeyEvent.VK_C);
				robot.delay(ms); // 等待 0.5秒
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
