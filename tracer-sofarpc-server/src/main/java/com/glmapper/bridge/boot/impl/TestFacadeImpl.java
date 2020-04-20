package com.glmapper.bridge.boot.impl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.glmapper.bridge.boot.facade.TestFacade;
import org.springframework.stereotype.Service;

/**
 * @author: glmapper (glmapper_2018@163.com) 2020/4/18 6:07 PM
 * @since:
 **/
@SofaService(interfaceType = TestFacade.class, bindings = { @SofaServiceBinding(bindingType = "bolt") })
@Service
public class TestFacadeImpl implements TestFacade {
    @Override
    public String SayHello(String name) {
        return "hello sofa rpc, " + name;
    }
}
