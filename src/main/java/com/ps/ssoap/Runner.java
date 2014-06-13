package com.ps.ssoap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class Runner implements ApplicationListener<ContextRefreshedEvent> {

    private Runnable runnableObject;

    public void setRunnableObject(Runnable runnableObject) {
        this.runnableObject = runnableObject;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
        if (runnableObject != null)
            runnableObject.run();
    }
}
