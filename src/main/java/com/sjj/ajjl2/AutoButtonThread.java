package com.sjj.ajjl2;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.lang.Thread.State;

public class AutoButtonThread implements Runnable {

	private Integer ms;
	boolean suspended = false;
	private String threadName;
	private Thread thread;

	public AutoButtonThread(Integer ms, String threadName) {
		this.ms = ms;
		this.threadName = threadName;
		System.out.println("Creating " + threadName);
	}

	@Override
	public void run() {
		Robot robot;
		try {
			robot = new Robot();
			while (true) {
				robot.keyPress(KeyEvent.VK_C);
				robot.delay(ms); // 等待 0.5秒
				synchronized (this) {
					while (suspended) {
						wait();
					}
				}
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Thread " + threadName + " interrupted.");
			e.printStackTrace();
		}

	}

	/**
	 * 开始
	 */
	public void start() {
		System.out.println("Starting " + threadName);
		if (thread == null) {
			thread = new Thread(this, threadName);
			thread.start();
		}
	}
	
	public void interrupt() {
		System.out.println("interrupt " + threadName);
		if (thread == null) {
			thread.interrupt();
		}
	}

	/**
	 * 暂停
	 */
	public void suspend() {
		suspended = true;
	}

	/**
	 * 继续
	 */
	public synchronized void resume() {
		suspended = false;
		notify();
	}

	public String getThreadName() {
		return thread == null ? null : thread.getName();
	}

	public Long getThreadId() {
		return thread == null ? null : thread.getId();
	}

	public boolean isLive() {
		return thread == null ? false : thread.isAlive();
	}

	public State getState() {
		return thread == null ? null : thread.getState();
	}

}
