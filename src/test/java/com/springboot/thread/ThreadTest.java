package com.springboot.thread;

/**
 * TODO 断点打到t.start(); 为什么线程没有执行?
 *
 *  * 有两种机制防止代码块受并发访问的干扰.
 *  * 1.synchronized
 *  * 2.bankLook.lock() bankLook.unlock()
 *  * 不推荐直接使用上边的两种方法,如果很简单的化另当别论.
 *  *
 *  *
 *  * 除非使用锁,或volatile修饰符,否则无法从多个线程安全地读取一个域
 *  *
 *  * 锁 和 条件
 *  *
 *  * 所有线程都被阻塞,这样的状态称为死锁.
 */
public class ThreadTest {

	public static void main(String[] args) {
		Thread t = new Thread() {
			@Override
			public void run() {
				for (int i = 0;i<100;i++){
					pong();
				}
			}
		};
		t.start();//这个是pongping,如果改掉用start方法结果是pingpong,如果start在这一行打断点,那么就会是pongping
		//直接run的话只是普通的方法调用,如果是start的话才是线程的启动
		System.out.println("ping");
	}

	private static void pong() {
		System.out.println("pong");
	}
}
