package com.edta.framework.component;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangluyao
 * @date 2022/4/28 13:48
 * @description
 */
@SpringBootTest(classes = {Starter.class})
@ActiveProfiles(profiles = {"redis", "db", "es"})
public class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void set() {
        List<String> keys = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            keys.add(new StringBuilder().append("test:key:")
                    .append(i)
                    .append(":string")
                    .toString());
        }
        Long startTime = System.currentTimeMillis();

        for (String key : keys) {
            stringRedisTemplate.opsForValue().set(key, key);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    @Test
    void get() {
        List<String> keys = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            keys.add(new StringBuilder().append("test:key:")
                    .append(i)
                    .append(":string")
                    .toString());
        }
        Long startTime = System.currentTimeMillis();

        for (String key : keys) {
            stringRedisTemplate.opsForValue().get(key);

        }
        System.out.println(System.currentTimeMillis() - startTime);
    }


    @Test
    void list() {
        String key = "hornbillv5:g111:task.info:list";
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        class TaskInfo {
            String taskType;
            String taskId;
            Long startTime;
            Long endTime;
            Map<String, Object> params;
        }

        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskId("fb_group_post_detect-1-227695-0-1-a9d261f2bc764539b99ceac59d031412");
        taskInfo.setTaskType("fb_group_post_detect");
        taskInfo.setStartTime(System.currentTimeMillis());
        taskInfo.setEndTime(0L);
        String json = "{\"targetAccountName\":\"陳智翔\",\"post_params\":\"eyJycF9hdXRob3I6MCI6IntcIm5hbWVcIjpcImF1dGhvclwiLFwiYXJnc1wiOlwiMTAwMDMyNzg2NjUzNjE3XCJ9In0=\",\"nickName\":\"\",\"effective_time_duration\":\"21600\",\"task_time\":\"1651101766\",\"corpusId\":\"1651053048104_55_4851272808203570825\",\"password\":\"aOWLXN9Bn30k\",\"guidEsId\":\"引导任务ID\",\"relogin\":false,\"bind_layer\":99999,\"phone\":\"\",\"identity\":\"100063588665850\",\"imei\":\"\",\"targetAccountFid\":\"100032786653617\",\"groupIdentity\":\"916467188376146\",\"email\":\"\",\"username\":\"100063588665850\"}";
        Map<String, Object> jsonObject = JSON.parseObject(json);
        taskInfo.setParams(jsonObject);
        stringRedisTemplate.opsForList().leftPush(key, JSON.toJSONString(taskInfo));
    }
}
