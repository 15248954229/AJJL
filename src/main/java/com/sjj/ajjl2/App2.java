package com.sjj.ajjl2;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App2 {
	public static void main(String[] args) {

		System.out.println("�����밴��Ƶ�ʣ���λ�����룩��");
		@SuppressWarnings("resource")
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
		System.out.println("�� ctrl+alt+Y ��ʼ");
		System.out.println("�� ctrl+alt+U ��ͣ");
		System.out.println("�� ctrl+alt+I ����");
		System.out.println("�� ctrl+alt+O �˳�");
		
		HotKey key = new HotKey();

		key.initHotkey(ms);

		// ����ģ�ⳤʱ��ִ�е�����
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (Exception ex) {

				break;

			}

		}

	}
}
