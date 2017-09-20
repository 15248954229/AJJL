package com.sjj.ajjl2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App2 {
	public static void main(String[] args) {

		System.out.println("请输入按键频率（单位：毫秒）：");
		@SuppressWarnings("resource")
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
		System.out.println("按 ctrl+alt+Y 开始");
		System.out.println("按 ctrl+alt+U 暂停");
		System.out.println("按 ctrl+alt+I 继续");
		System.out.println("按 ctrl+alt+O 退出");
		
		HotKey key = new HotKey();

		key.initHotkey(ms);

		// 下面模拟长时间执行的任务
		while (true) {

			try {

				Thread.sleep(10000);

			} catch (Exception ex) {

				break;

			}

		}

	}
}
