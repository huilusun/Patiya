package com.huiju.blackbrin.listener;
import org.elasticsearch.client.transport.TransportClient;

import javax.servlet.ServletContextEvent;

import javax.servlet.ServletContextListener;
import java.util.HashSet;

/**
 * Created by xpchen on 17/2/28.
 */
public class ContextDestoryListener  implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        for(TransportClient client : allClients){
            client.close();
        }
    }


    private static HashSet<TransportClient> allClients = new HashSet<TransportClient>();

    public static void addCloseable(TransportClient client){
        allClients.add(client);
    }


}
