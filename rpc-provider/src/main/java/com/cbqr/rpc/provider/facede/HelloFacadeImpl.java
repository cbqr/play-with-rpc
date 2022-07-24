package com.cbqr.rpc.provider.facede;

import com.cbqr.rpc.provider.annotation.RpcService;
import com.cbqr.rpc.provider.facade.HelloFacade;

/**
 * HelloFacadeImpl
 *
 * @author Dave Liu
 * @since 2022-07-22
 */
@RpcService(serviceInterface = HelloFacade.class, serviceVersion = "1.0.0")
public class HelloFacadeImpl implements HelloFacade {
    @Override
    public String hello(String name) {
        return "hello" + name;
    }
}
