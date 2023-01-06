package disconnection;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.*;

@Slf4j
public class ScheduleThreadTest {

    /**
     * 排队执行，等待1秒执行一次
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testFuture() throws ExecutionException, InterruptedException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        List<String> taskList= Lists.newArrayList("1号","2号","5号","7号");
        Queue<String> queue = new ConcurrentLinkedDeque<>(taskList);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        while (!queue.isEmpty()) {
            ScheduledFuture<String> scheduledFuture = executorService.schedule(() -> {
                System.out.println("时间:" + sdf.format(new Date())+"线程" + Thread.currentThread().getName() + " 执行了task: " + queue.poll());
                return "call";
            }, 1, TimeUnit.SECONDS);
            System.out.println(scheduledFuture.get() + " " + queue.size());
        }

        executorService.shutdown();
    }


    public static void main(String[] args) {
        log.info("准备执行任务");
        List<String> taskList= Lists.newArrayList("1号","2号","5号","7号","10号","11号");
        ScheduledExecutorService pool= Executors.newScheduledThreadPool(1);

        /**
         * 一次性任务，执行完结束
         */
//        pool.schedule(() ->
//                taskList.forEach(task ->
//                        log.info(Thread.currentThread().getName() + " "+System.currentTimeMillis()+"当前执行的任务是" + task)), 1, TimeUnit.SECONDS);
//        pool.shutdown();

        /**
         * 周期性任务，每5秒运行一次
         */
        pool.scheduleAtFixedRate(() ->
                taskList.forEach(task ->
                        log.info("线程 {} "+ " 当前执行的任务是 {}" ,Thread.currentThread().getName(), task)), 0,3, TimeUnit.SECONDS);//0：初始等待时间，3：每5秒执行一次

        /**
         * 周期性任务，任务之间隔5秒
         */
//        Queue<String> queue = new ConcurrentLinkedDeque<>(taskList);
//        pool.scheduleWithFixedDelay(() -> {
//            if(!queue.isEmpty()){
//                log.info(Thread.currentThread().getName() + " 当前执行的任务是" + queue.poll());
//            }else {
//                //pool.shutdown();//无任务关闭定时调度
//                //log.info("无任务休息一下");
//            }
//        }, 0,3, TimeUnit.SECONDS);//0：初始等待时间，3：每个任务间隔5秒
    }
}
