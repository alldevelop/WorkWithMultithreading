package com.mainacad.guitar;


import com.mainacad.model.ConnectionInfo;
import com.mainacad.service.FileServiceHW;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;


public class MultithreadWrapper extends Thread {

    private final String threadName;
    private final ConnectionInfo connectionInfo;
    private List connectionIpList;
    private static final Logger LOG =
            Logger.getLogger(MultithreadWrapper.class.getName());
    private Semaphore semaphore;
            int num = 0;


    public MultithreadWrapper
            (String threadName, ConnectionInfo connectionInfo, List connectionIpList, Semaphore semaphore) {
        this.threadName = threadName;
        this.connectionInfo = connectionInfo;
        this.connectionIpList = connectionIpList;
        this.semaphore = semaphore;
    }


    @Override
    public void run()
    {
        while(num < 10) {
            try {
                semaphore.acquire();

                LOG.info(threadName + " is running!");
                sleep(5000);
                num++;
                FileServiceHW.writeTextToFile(
                        connectionInfo.toString(), "multi.txt", true);
                connectionIpList.add(connectionInfo.getUserIp());
                LOG.info(threadName + " is finished");
                semaphore.release();
                sleep(1000);
            }
        catch (InterruptedException e) {
        e.printStackTrace();
    } //LOG.info(threadName + " is !");
   }
    }
}
