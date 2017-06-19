package hadoop.ray.hadoop2.zookeeper.watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class ZkWatch implements Watcher {

	@Override
	public void process(WatchedEvent event) {
		System.out.println( event.toString()  );
	}

}
