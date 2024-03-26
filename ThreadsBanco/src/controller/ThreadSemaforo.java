package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadSemaforo extends Thread {
	private int idThread;
	private Semaphore semaforo;
	Random random = new Random();

	public ThreadSemaforo(int idThread, Semaphore semaforo) {
		this.semaforo = semaforo;
		this.idThread = idThread;
	}

	@Override
	public void run() {
		if (idThread % 3 == 0) {
			igual0();
		} else if (idThread % 3 == 1) {
			igual1();
		} else if (idThread % 3 == 2) {
			igual2();
		}
	}

	public void igual1() {// caso a thread id mod 3 seja 0
		int sleep = (int) ((Math.random() * 801) + 200);// calcular sleep 200 a 1000 ms
		calculo(sleep);

		try {// semaforo transacao
			semaforo.acquire();
			transacao(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

		sleep = (int) ((Math.random() * 1000) + 200);
		calculo(sleep);

		try {
			semaforo.acquire();
			transacao(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

	}

	public void igual2() {// caso a thread id mod 3 seja 1
		int sleep = (int) ((Math.random() * 1001) + 500);// calcular sleep 500 a 1500 ms
		calculo(sleep);

		try {
			semaforo.acquire();
			transacao(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

		sleep = (int) ((Math.random() * 1001) + 500);
		calculo(sleep);
		
		try {
			semaforo.acquire();
			transacao(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

		sleep = (int) ((Math.random() * 1001) + 500);
		calculo(sleep);

		try {
			semaforo.acquire();
			transacao(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	public void igual0() {// caso a thread id mod 3 seja 2
		int sleep = (int) ((Math.random() * 1001) + 1000);// calcular sleep 1000 a 2000 ms
		calculo(sleep);

		try {
			semaforo.acquire();
			transacao(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

		sleep = (int) ((Math.random() * 1001) + 1000);// calcular sleep 1000 a 2000 ms
		calculo(sleep);

		try {
			semaforo.acquire();
			transacao(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

		sleep = (int) ((Math.random() * 1001) + 1000);// calcular sleep 1000 a 2000 ms
		calculo(sleep);

		try {
			semaforo.acquire();
			transacao(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	public void calculo(int sleep) {
		try {
			System.out.println("Thread " + idThread + " calculando");
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void transacao(int sleep) {
		try {
			System.out.println("Thread " + idThread + " realizando transacoes");
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
