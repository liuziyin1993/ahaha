package com.aisile.article;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleApplicationTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void text1(){
        redisTemplate.boundValueOps("name").set("终于出来了，好爽啊");

        Set keys = redisTemplate.keys("*");
        keys.stream().forEach(System.out::println);
    }
}
