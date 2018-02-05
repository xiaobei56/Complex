package cn.gridlife.generallibrary.custom;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by BZB on 2018/1/19.
 * project Speach
 */

public class CustomThreadPool {
    private static int CORE_POOL_SIZE = 5;
    /**
     * 线程池最大线程数
     */
    private static int MAX_POOL_SIZE = 100;
    /**
     * 额外线程空状态生存时间
     */
    private static int KEEP_ALIVE_TIME = 30 * 1000;

    private static ThreadPoolExecutor threadPool;
    /**
     * 阻塞队列。当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程。
     */
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(CORE_POOL_SIZE);
    private static ThreadFactory factory = new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger();
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ThreadPool thread: " + integer.getAndIncrement());
        }
    };

    static {
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue, factory);
    }
}
