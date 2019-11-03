package com.mainacad;

import com.mainacad.guitar.MultithreadWrapper;
import com.mainacad.helper.ConnectionInfoHelper;
import com.mainacad.model.ConnectionInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;


public class AppFunner {
    private static final Logger LOG = Logger.getLogger(AppFunner.class.getName());

    public static void main(String[] args) {
        List<String> connectionIpList = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();

        Semaphore semaphore = new Semaphore(10);


        for (int i=0; i<=100; i++){
            ConnectionInfo connectionInfo = ConnectionInfoHelper.getRandomConnectionInfo();
            MultithreadWrapper multithreadWrapper =
                    new MultithreadWrapper("thread " + i, connectionInfo, connectionIpList, semaphore);

              multithreadWrapper.start();
        }

        while (threadsAlive(threads)) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //LOG.info(connectionIpList.size()+ "connections were written to file");

    }
    private static boolean threadsAlive(List<Thread> threads){
        for (Thread thread: threads) {
            if (thread.isAlive() || thread.getState().equals(Thread.State.NEW)) {
                return true;
            }
        }
        return false;
    }

}