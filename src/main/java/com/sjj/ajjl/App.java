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
		System.out.println("�����밴��Ƶ�ʣ���λ�����룩��");
		Scanner sc = new Scanner(System.in);
		String content = sc.nextLine();
		System.out.println("��������Ϊ��" + content + "����");
		String rex = "^\\+?[1-9][0-9]*$";// �������жϡ�
		Pattern p = Pattern.compile(rex);
		Matcher m = p.matcher(content);
		int ms = 500;
		if (m.find()) {
			try {
				ms = Integer.parseInt(content);
			} catch (Exception e) {
				System.out.println("����ת������Ĭ����500����Ƶ�ʽ��а���");
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("���벻�Ϸ���Ĭ����500����Ƶ�ʽ��а���");
		}

		Robot robot;
		try {
			robot = new Robot();
			while (true) {
				robot.keyPress(KeyEvent.VK_C);
				robot.delay(ms); // �ȴ� 0.5��
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
