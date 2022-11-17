package com.superarilo.utils;

import com.github.wujun234.uid.impl.CachedUidGenerator;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

    private final CachedUidGenerator cachedUidGenerator;
    public IdGenerator(CachedUidGenerator cachedUidGenerator) {
        this.cachedUidGenerator = cachedUidGenerator;
    }
    public long nextId() {
        return cachedUidGenerator.getUID();
    }
    public String parse(long uid) {
        return cachedUidGenerator.parseUID(uid);
    }
}

