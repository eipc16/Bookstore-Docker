package org.pietrzakp.proxyservice.proxy.service;

import org.pietrzakp.proxyservice.proxy.adapters.ProxyClientAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProxyService {

    private ProxyClientAdapter proxyClientAdapter;

    public Object get() {
        return proxyClientAdapter.getRequest();
    }

    public void test() {
        ProxyService proxyService = new ProxyService();
        proxyService.setProxyClientAdapter(null);
    }

    @Autowired
    public void setProxyClientAdapter(ProxyClientAdapter proxyClientAdapter) {
        this.proxyClientAdapter = proxyClientAdapter;
    }
}
