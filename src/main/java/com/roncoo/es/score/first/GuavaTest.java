package com.roncoo.es.score.first;

import com.google.common.util.concurrent.RateLimiter;
import com.sun.org.apache.xpath.internal.operations.String;

public class GuavaTest   {

    //每秒只发出5个令牌
    RateLimiter rateLimiter = RateLimiter.create(5.0);

    /**
     * 尝试获取令牌
     * @return
     */
    public boolean tryAcquire(){
        return rateLimiter.tryAcquire();

    }

    public void testAcquire() {
        RateLimiter limiter = RateLimiter.create(1);

        for(int i = 1; i < 10; i = i + 2 ) {
            double waitTime = limiter.acquire(i);
            System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + waitTime);
        }
    }
    public static void main(String[] args) {
        GuavaTest guavaTest = new GuavaTest();

        guavaTest.testAcquire();

        for (int i = 0; i < 100000; i++) {

        }

    }


}
