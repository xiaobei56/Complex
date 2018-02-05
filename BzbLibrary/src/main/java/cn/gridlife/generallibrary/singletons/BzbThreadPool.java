package cn.gridlife.generallibrary.singletons;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by BZB on 2018/1/19.
 * project Speach
 */

public class BzbThreadPool {
    ExecutorService fixThreadPool= Executors.newFixedThreadPool(5);

}
