package ali.rule.r1.thread.threadpoolexecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

// 当线程过多时，异常处理类
public class CustomRejectedExecutionHandler implements RejectedExecutionHandler{

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		// 记录异常 报警处理等 ，任务数提交过多
		MyThread m = (MyThread)r;
		System.out.println(m.getName()+" 失败............." );
	}
}
