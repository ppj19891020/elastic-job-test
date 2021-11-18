package com.demo.config;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.demo.job.DemoJob;
import com.demo.job.MyElasticJobListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author xiangnan
 * @date 2021年11月18日 2:48 下午
 */
@Configuration
public class JobConfig {

    @Resource
    private ZookeeperRegistryCenter registryCenter;

    @Value("${demo.demoJob.name}")
    private String demoJobName;

    @Value("${demo.demoJob.cron}")
    private String demoJobCron;

    @Value("${demo.demoJob.shardingTotalCount}")
    private int demoJobShardingTotalCount;

    @Bean(initMethod = "init")
    public JobScheduler demoJobScheduler(final DemoJob demoJob){
        MyElasticJobListener elasticJobListener = new MyElasticJobListener();
        return new SpringJobScheduler(demoJob,registryCenter,liteJobConfiguration(),elasticJobListener);
    }

    private LiteJobConfiguration liteJobConfiguration(){
        return LiteJobConfiguration.newBuilder(
                new SimpleJobConfiguration(
                        JobCoreConfiguration.newBuilder(demoJobName,demoJobCron,demoJobShardingTotalCount).build() ,DemoJob.class.getCanonicalName()
                )).overwrite(true).disabled(true).build();
    }
}
