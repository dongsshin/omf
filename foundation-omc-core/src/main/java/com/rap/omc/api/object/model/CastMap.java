/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ObjectMap.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 6. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.object.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <pre>
 * Class : ObjectMap
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class CastMap<K, V> extends LinkedHashMap<K, V> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1148713106515796982L;

    public CastMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public CastMap(int initialCapacity) {
        super(initialCapacity);
    }

    public CastMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    public CastMap() {
        super();
    }

    public CastMap(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }
}
