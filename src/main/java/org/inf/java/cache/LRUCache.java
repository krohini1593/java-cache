package org.inf.java.cache;

public class LRUCache extends AbstractCache implements Cache{
    LRUCache(long defaultTTL, int capacity) {
        super(defaultTTL, capacity);
    }

    @Override
    protected void evict() {

    }
}
