package com.horsecoder.gateway.test.starter.dns;

import sun.net.spi.nameservice.NameService;
import sun.net.spi.nameservice.NameServiceDescriptor;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class DNSNameService implements NameServiceDescriptor, NameService {

    @Override
    public NameService createNameService() throws Exception {
        return this;
    }

    @Override
    public String getProviderName() {
        return "local-dns-provider";
    }

    @Override
    public String getType() {
        return "dns";
    }

    @Override
    public InetAddress[] lookupAllHostAddr(String host) throws UnknownHostException {
        return "dev.center.horsecoder.com".equals(host) ||
                "dev.gateway.horsecoder.com".equals(host) ||
                "dev.database.horsecoder.com".equals(host) ?
                InetAddress.getAllByName("127.0.0.1") :
                new InetAddress[0];
    }

    @Override
    public String getHostByAddr(byte[] bytes) throws UnknownHostException {
        return null;
    }
}
