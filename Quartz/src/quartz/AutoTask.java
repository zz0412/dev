package quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class AutoTask extends QuartzJobBean {

    public void everyHour() {
        System.out.println("===容器启动一分钟后开始执行，每小时执行一次任务===");
    }

    public void everyQuarter() {
        System.out.println("===容器启动一分钟后开始执行，每刻钟执行一次任务===");
    }

    public void everyWeek() {
        System.out.println("===容器启动一分钟后开始执行，每小时执行一次任务===");
    }

    public void specificTime() {
        System.out.println("===容器启动一分钟后开始执行，每小时执行一次任务===");
    }

    @Override
    protected void executeInternal(JobExecutionContext arg0)
            throws JobExecutionException {
    }

}
