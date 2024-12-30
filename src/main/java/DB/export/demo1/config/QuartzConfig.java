package DB.export.demo1.config;

import DB.export.demo1.scheduler.ExportJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail exportJobDetail() {
        return JobBuilder.newJob(ExportJob.class)
                .withIdentity("exportJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger exportJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(exportJobDetail())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMinutes(1)
                        .repeatForever())
                .build();
    }
}
