package com.style.threadpool;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * Created by xiajun on 2017/2/14.
 */

public class CachedThreadPoolManager {
    private Map<String, List<Runnable>> mTaskMap = new HashMap();

    private ExecutorService cachedThreadPool;
    private static CachedThreadPoolManager mInstance;

    public static CachedThreadPoolManager getInstance() {
        if (mInstance == null) {
            mInstance = new CachedThreadPoolManager();
        }
        return mInstance;
    }
    public ExecutorService getThreadPool() {
        if (cachedThreadPool == null)
            cachedThreadPool = Executors.newCachedThreadPool();
        return cachedThreadPool;
    }
    public void runTask(String tag, Callable callBack) {
        getThreadPool().submit(callBack);
    }

    public void runTask(String tag, Runnable callBack) {
        getThreadPool().submit(callBack);
    }
    protected void addTask(String tag, Runnable subscription) {
        /*List<Subscription> mSubscriptions = mTaskMap.get(tag);
        if (mSubscriptions == null)
            mSubscriptions = new ArrayList<>();
        mSubscriptions.add(subscription);
        mTaskMap.put(tag, mSubscriptions);*/
    }

    public void removeTask(String tag) {
       /* List<Runnable> mSubscriptions = mTaskMap.get(tag);
        if (mSubscriptions != null) {
            for (Runnable s : mSubscriptions) {
                if (s != null && s.isUnsubscribed()) {
                    s.unsubscribe();
                }
            }
        }*/
    }
    public void shutdown() {
        //关闭线程池
        if (cachedThreadPool != null)
            cachedThreadPool.shutdown();
    }
}
