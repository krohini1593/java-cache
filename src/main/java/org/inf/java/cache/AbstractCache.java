package org.inf.java.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

abstract class AbstractCache implements Cache{

    protected Map<String, Value> kvData;

    protected Map<String, Long> ttlMap;
    protected final long defaultTTL;
    protected final int capacity;

    AbstractCache(long defaultTTL, int capacity) {
        this.defaultTTL = defaultTTL;
        this.capacity = capacity;
        kvData = new HashMap<>(capacity);
    }

    public Optional<Value> get(String key) {
        Value v = kvData.get(key);
        if(v == null) {
            return Optional.empty();
        }
        // Update key TTL on access
        ttlMap.put(key, defaultTTL);
        return Optional.of(v);
    }

    public void set(String key, Value v) {
        if (isFull()) {
            evict();
        }
        v = new Value(v.value());
        kvData.put(key, v);
        ttlMap.put(key, defaultTTL);
    }

    protected boolean isFull() {
        return kvData.size()==capacity;
    }

    protected abstract void evict();
}
