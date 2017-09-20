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
			System.out.println("监听 ctrl+alt+Y 开始");
			// 开启自动按键线程
			if (thread == null || !thread.isLive()) {
				thread = new AutoButtonThread(ms, "auto_button");
				thread.start();
				threadName = thread.getThreadName();
				threadId = thread.getThreadId();
				autoState = thread.getState();
			} else {
				System.out.println("进程" + threadName + "已经存在,ID:" + threadId);
			}
			break;

		case KEY_2:
			System.out.println("监听 ctrl+alt+U 暂停");
			if (thread == null || !thread.isLive()) {
				System.out.println("进程未开始，请按 ctrl+alt+Y 开始");
			} else {
				if (autoState != null && autoState.equals(State.RUNNABLE)) {
					thread.suspend();
				} else {
					System.out.println("线程不在运行状态，不能暂停");
				}
			}
			break;

		case KEY_3:
			System.out.println("监听 ctrl+alt+I 继续");
			if (thread == null || !thread.isLive()) {
				System.out.println("进程未开始，请按 ctrl+alt+Y 开始");
			} else {
				thread.resume();
			}
			break;

		case KEY_4:
			System.out.println("监听 ctrl+alt+O 退出");
			System.out.println("系统退出..........");
			if (thread == null || !thread.isLive()) {
				System.out.println("进程未开始，请按 ctrl+alt+Y 开始");
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
		// 设置自动按键线程的Id
		this.ms = ms;
		// 参数KEY_1表示改组热键组合的标识，第二个参数表示组合键，如果没有则为0，该热键对应ctrl+alt+I
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
