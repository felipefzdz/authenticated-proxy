package org.example;

import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.ProxyAuthenticator;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;

public class Main {

    public static void main(String[] args) {
        ProxyAuthenticator proxyAuthenticator = new ProxyAuthenticator() {
            @Override
            public boolean authenticate(String username, String password) {
                return "user".equals(username) && "password".equals(password);
            }

            @Override
            public String getRealm() {
                return null;
            }
        };
        HttpProxyServer proxyServer = DefaultHttpProxyServer.bootstrap()
            .withPort(5003)
            .withProxyAuthenticator(proxyAuthenticator)
            .withTransparent(true)
            .start();

        Runtime.getRuntime().addShutdownHook(new Thread(proxyServer::stop));
    }
}
