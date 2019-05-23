package com.springboot.thread;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class RemoveIntger2Thread implements Callable<List> {

    private List<Integer> list;

    public RemoveIntger2Thread(List<Integer> list) {
        this.list = list;
    }

    public List<Integer> getList(){
        return list;
    }

    @Override
    public List call() throws Exception {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 2) {
                iterator.remove();
            }
        }
        return list;
    }
}
