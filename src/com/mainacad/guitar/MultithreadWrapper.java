package com.mainacad.guitar;


import com.mainacad.model.ConnectionInfo;
import com.mainacad.service.FileServiceHW;

import java.util.List;
import java.util.logging.Logger;

public class MultithreadWrapper extends Thread {

    private final String threadName;
    private final ConnectionInfo connectionInfo;
    private static final Logger LOG =
            Logger.getLogger(MultithreadWrapper.class.getName());

    private List connectionIpList;

    public MultithreadWrapper
            (String threadName, ConnectionInfo connectionInfo, List connectionIpList) {
        this.threadName = threadName;
        this.connectionInfo = connectionInfo;
        this.connectionIpList = connectionIpList;
    }

    @Override
    public void run() {
        LOG.info(threadName + " was started!");

        FileServiceHW.writeTextToFile(
                connectionInfo.toString(), "multi.txt", true);
        connectionIpList.add(connectionInfo.getUserIp());
    }
}
