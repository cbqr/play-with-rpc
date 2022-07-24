package com.cbqr.rpc.provider.registry;

import com.cbqr.rpc.common.ServiceMeta;

import java.io.IOException;

/**
 * EurekaRegistryService
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
public class EurekaRegistryService implements RegistryService {

    public EurekaRegistryService(String registryAddr) {
        // TODO
    }

    @Override
    public void register(ServiceMeta serviceMeta) throws Exception {

    }

    @Override
    public void unRegister(ServiceMeta serviceMeta) throws Exception {

    }

    @Override
    public ServiceMeta discovery(String serviceName, int invokerHashCode) throws Exception {
        return null;
    }

    @Override
    public void destroy() throws IOException {

    }
}
