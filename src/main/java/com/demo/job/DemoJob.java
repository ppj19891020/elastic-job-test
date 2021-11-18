package com.demo.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xiangnan
 * @date 2021年11月18日 2:44 下午
 */
@Slf4j
@Component
public class DemoJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("<====Demo Job Begin====>");
        log.info(String.format("------Thread ID: %s, 任务总片数: %s, 当前分片项: %s",
                Thread.currentThread().getId(), shardingContext.getShardingTotalCount(), shardingContext.getShardingItem()));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("<====Demo Job End====>");
    }

}
