package com.demo.action;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.demo.job.SayHelloJob;
import com.demo.service.QuartService;
import com.demo.service.UserService;
import com.demo.service.impl.QuartServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.quartz.CronScheduleBuilder.cronSchedule;

public class InitQuartzAction extends ActionSupport {

    private QuartService QuartService;

    public QuartService getQuartService() {
        return QuartService;
    }

    public void setQuartService(com.demo.service.QuartService quartService) {
        QuartService = quartService;
    }

    public String scheduleJobs() throws SchedulerException {
        QuartService.scheduleJobs();
        return "InitQuartzAction";
    }


//    public String addJob(String jobJava,String jobDesc,String jobName, @RequestParam(name = "group",defaultValue = "group1")String group,
//                         @RequestParam(name = "cron") String cron) throws SchedulerException, ClassNotFoundException {
//        Class jobClass=Class.forName(jobJava);
//        Scheduler scheduler = schedulerFactoryBean.getScheduler();
//        JobDetail jobDetail = JobBuilder.newJob(jobClass)
//            .withDescription(jobDesc)
//            .withIdentity(jobName, group)
//            .build();
//        Trigger trigger = TriggerBuilder.newTrigger()
//            .forJob(jobDetail)
//            .withSchedule(cronSchedule(cron))
//            .build();
//        if(!scheduler.checkExists(JobKey.jobKey(jobName,group))){
//            scheduler.scheduleJob(jobDetail,trigger);
//        }
//        scheduler.start();
//        return "添加成功";
//    }

//    public String resumeJob(String jobName, String jobGroup) throws SchedulerException {
//        Scheduler scheduler = schedulerFactoryBean.getScheduler();
//        JobKey jobKey=JobKey.jobKey(jobName,jobGroup);
//        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
//        //将trigger表中的state重置
//        triggers.forEach(x->{
//            try {
//                scheduler.resetTriggerFromErrorState(x.getKey());
//            } catch (SchedulerException e) {
//                Logs.error("重置定时任务异常："+e.getMessage(),e);
//            }
//        });
//        scheduler.resumeJob(jobKey);
//        return "success";
//    }
//
//    public String removeJob(String jobName,String jobGroupName) throws SchedulerException {
//        Scheduler scheduler=schedulerFactoryBean.getScheduler();
//        TriggerKey tiger= new TriggerKey(jobName,TRIGGER_GROUP_NAME);
//        scheduler.pauseTrigger(tiger);
//        scheduler.unscheduleJob(tiger);
//        scheduler.deleteJob(new JobKey(jobName,jobGroupName));
//        return "success";
//    }
//
//    public String modifyJob(String jobName,String jobGroupName,String triggerName,String triggerGroupName,String cron){
//        try {
//            Logs.info("jobName="+jobName+"; jobGroupName="+jobGroupName+"; triggerName="+triggerName+"; triggerGroupName="+triggerGroupName+"; cron="+cron);
//            TriggerKey triggerKey =null;
//            Scheduler scheduler = schedulerFactoryBean.getScheduler();
//            if(StringUtils.hasText(triggerName)||StringUtils.hasText(triggerGroupName)){
//                triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
//            }else {
//                //通过任务名称和任务主修改
//                List<? extends Trigger>triggers= scheduler.getTriggersOfJob(JobKey.jobKey(jobName,jobGroupName));
//                if(triggers!=null&&triggers.size()>0){
//                    triggerKey=triggers.get(0).getKey();
//                }
//            }
//            Logs.info("triggerName= "+triggerKey.getName()+"; triggerGroupName="+triggerKey.getGroup());
//            CronTrigger cronTrigger= (CronTrigger) scheduler.getTrigger(triggerKey);
//            if(cronTrigger==null){
//                return "定时任务出发时间为空！更新失败";
//            }
//            String oldTime=cronTrigger.getCronExpression();
//            if(!oldTime.equals(cron)){//判断表达式是否一致，如果一致则不需要修改
//                TriggerBuilder<Trigger> triggerBuilder=TriggerBuilder.newTrigger();
//                triggerBuilder.withIdentity(triggerKey.getName(),triggerKey.getGroup());
//                triggerBuilder.startNow();
//                //触发器设定
//                triggerBuilder.withSchedule(cronSchedule(cron));
//                // 创建Trigger对象
//                cronTrigger = (CronTrigger) triggerBuilder.build();
//                scheduler.resumeTrigger(triggerKey);
//                scheduler.rescheduleJob(triggerKey,cronTrigger);
//            }
//        }catch (Exception e){
//            Logs.error("修改定时任务异常："+e.getMessage(),e);
//            return "failed";
//        }
//        return "success";
//    }
}
