package com.wt.test.spring.wash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: qiyu
 * @date: 2020/7/2 16:10
 * @description:
 */
@Slf4j
public class WashTest {

    private static volatile Semaphore semaphore = new Semaphore(3, true);

    private static volatile List<Integer> curr = new ArrayList<>(3);

    private static AtomicLong seq = new AtomicLong(0L);

    private static volatile ArrayBlockingQueue<WashJob> maleQueue = new ArrayBlockingQueue<>(10000);

    private static volatile ArrayBlockingQueue<WashJob> femaleQueue = new ArrayBlockingQueue<>(10000);

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 1L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) {
       Thread decisionThread =  new Thread(() -> {
            while (true) {
                try {
                    boolean result = semaphore.tryAcquire();
                    if (!result) {
                        Thread.sleep(1000L);
                        continue;
                    }
                    if (CollectionUtils.isEmpty(curr)) {
                        WashJob male = maleQueue.peek();
                        WashJob female = femaleQueue.peek();
                        if (male != null && female != null) {
                            if (male.getSeq() < female.getSeq()) {
                                executor.submit(maleQueue.remove());
                                curr.add(Integer.valueOf(0));
                            } else {
                                executor.submit(femaleQueue.remove());
                                curr.add(Integer.valueOf(1));
                            }
                        } else if (male != null && female == null) {
                            executor.submit(maleQueue.remove());
                            curr.add(Integer.valueOf(0));
                        } else if (male == null && female != null) {
                            executor.submit(femaleQueue.remove());
                            curr.add(Integer.valueOf(1));
                        } else
                            semaphore.release();
                    } else if (curr.contains(Integer.valueOf(0))) {
                        WashJob male = maleQueue.peek();
                        if (male != null) {
                            executor.submit(maleQueue.remove());
                            curr.add(Integer.valueOf(0));
                        } else
                            semaphore.release();
                    } else {
                        WashJob female = femaleQueue.peek();
                        if (female != null) {
                            executor.submit(femaleQueue.remove());
                            curr.add(Integer.valueOf(1));
                        } else
                            semaphore.release();
                    }
                } catch (InterruptedException e) {
                    log.error("决策程被中断");
                }
            }
        });
        decisionThread.setPriority(Thread.MAX_PRIORITY);
        decisionThread.start();
        Random genderRad = new Random();
        Random durationRad = new Random();
        while (true) {
            int gender = genderRad.nextInt(2);
            if (gender == 0) {
                WashJob male = WashJob.builder()
                        .seq(seq.incrementAndGet()).gender(gender).duration(1)
                        .curr(curr).semaphore(semaphore).build();
                maleQueue.add(male);
            } else {
                WashJob female = WashJob.builder()
                        .seq(seq.incrementAndGet()).gender(gender).duration(1)
                        .curr(curr).semaphore(semaphore).build();
                femaleQueue.add(female);
            }
            try {
                Thread.sleep(durationRad.nextInt(3) * 1000L);
            } catch (InterruptedException e) {
                log.error("主线程被中断");
            }
        }
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
class WashJob implements Runnable {
    private long seq;
    private int gender;
    private int duration;
    private Semaphore semaphore;
    private List<Integer> curr;

    public void run() {
        log.info(String.format("编号%d%s开始洗澡。", seq, gender == 0 ? "男士" : "女士"));
        try {
            Thread.sleep(duration * 1000L);
        } catch (InterruptedException e) {
            log.error(String.format("编号%d%s洗澡被中断", seq, gender == 0 ? "男士" : "女士"), e);
        } finally {
            curr.remove(Integer.valueOf(gender));
            log.info(String.format("编号%d%s洗澡结束。", seq, gender == 0 ? "男士" : "女士"));
            semaphore.release();
        }
    }
}
