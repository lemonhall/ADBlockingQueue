import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lemonhall on 15/7/10.
 */
public class ADBlockingQueue extends LinkedBlockingQueue {
    private HashMap hm = new HashMap();

    public ADBlockingQueue(int i) {
        super(i);
    }

    @Override
    public void put(Object o) throws InterruptedException {
        boolean hasKey = hm.containsKey(o);
        if(hasKey){
            hm.put(o,o);
        }else{
            super.put(o);
            hm.put(o,o);
        }
    }

    @Override
    public Object take() throws InterruptedException {
        Object o = super.take();
        Object value = hm.get(o);
                       hm.remove(o);
        System.out.println("queue size is:" + super.size());
        System.out.println("hashmap size is:"+hm.size());
        return value;
    }
}
