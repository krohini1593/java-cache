package org.inf.java.cache;

import java.util.Optional;

public interface Cache {

    Optional<Value> get(String key);
    void set(String key, Value value);
}
