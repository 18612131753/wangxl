package ali.rule.r1.thread.atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class TestAtomicLong {

	public static void main(String[] args) {
		AtomicLong l = new AtomicLong(1);
		System.out.println( l.incrementAndGet() );
		System.out.println( l.incrementAndGet() );
		
		LongAdder la = new LongAdder( );
		la.increment(); //自增
		System.out.println( la.longValue() );
		la.add(2);
		System.out.println( la.longValue() );
	}

}
