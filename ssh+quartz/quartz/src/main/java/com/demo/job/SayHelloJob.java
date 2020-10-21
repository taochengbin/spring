package com.demo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SayHelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext)
        throws JobExecutionException {
        //todo
        System.out.println("Hello word！");
    }
}

