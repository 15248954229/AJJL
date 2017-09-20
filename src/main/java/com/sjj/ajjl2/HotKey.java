package com.sjj.ajjl2;

import java.lang.Thread.State;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class HotKey implements HotkeyListener {

	static final int KEY_1 = 88;
	static final int KEY_2 = 89;
	static final int KEY_3 = 90;
	static final int KEY_4 = 91;

	Integer ms;
	AutoButtonThread thread = null;
	String threadName;
	Long threadId;
	State autoState;

	public void onHotKey(int key) {
		switch (key) {

		case KEY_1:
			System.out.println("���� ctrl+alt+Y ��ʼ");
			// �����Զ������߳�
			if (thread == null || !thread.isLive()) {
				thread = new AutoButtonThread(ms, "auto_button");
				thread.start();
				threadName = thread.getThreadName();
				threadId = thread.getThreadId();
				autoState = thread.getState();
			} else {
				System.out.println("����" + threadName + "�Ѿ�����,ID:" + threadId);
			}
			break;

		case KEY_2:
			System.out.println("���� ctrl+alt+U ��ͣ");
			if (thread == null || !thread.isLive()) {
				System.out.println("����δ��ʼ���밴 ctrl+alt+Y ��ʼ");
			} else {
				if (autoState != null && autoState.equals(State.RUNNABLE)) {
					thread.suspend();
				} else {
					System.out.println("�̲߳�������״̬��������ͣ");
				}
			}
			break;

		case KEY_3:
			System.out.println("���� ctrl+alt+I ����");
			if (thread == null || !thread.isLive()) {
				System.out.println("����δ��ʼ���밴 ctrl+alt+Y ��ʼ");
			} else {
				thread.resume();
			}
			break;

		case KEY_4:
			System.out.println("���� ctrl+alt+O �˳�");
			System.out.println("ϵͳ�˳�..........");
			if (thread == null || !thread.isLive()) {
				System.out.println("����δ��ʼ���밴 ctrl+alt+Y ��ʼ");
			} else {
				thread.interrupt();
			}
			destroy();
		}
	}

	void destroy() {
		JIntellitype.getInstance().unregisterHotKey(KEY_1);
		JIntellitype.getInstance().unregisterHotKey(KEY_2);
		JIntellitype.getInstance().unregisterHotKey(KEY_3);
		JIntellitype.getInstance().unregisterHotKey(KEY_4);
		System.exit(0);
	}

	void initHotkey(Integer ms) {
		// �����Զ������̵߳�Id
		this.ms = ms;
		// ����KEY_1��ʾ�����ȼ���ϵı�ʶ���ڶ���������ʾ��ϼ������û����Ϊ0�����ȼ���Ӧctrl+alt+I
		JIntellitype.getInstance().registerHotKey(KEY_1, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,
				(int) 'Y');
		JIntellitype.getInstance().registerHotKey(KEY_2, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,
				(int) 'U');
		JIntellitype.getInstance().registerHotKey(KEY_3, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,
				(int) 'I');
		JIntellitype.getInstance().registerHotKey(KEY_4, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,
				(int) 'O');

		JIntellitype.getInstance().addHotKeyListener(this);
	}
}
