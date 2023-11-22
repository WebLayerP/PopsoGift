package it.popso.popsogift.utils;

import java.math.BigDecimal;

public class CastObjectsUtils {

    private CastObjectsUtils(){
        // private constructor
    }

    public static int castObjectIntValue(Object o){
        if(o instanceof Float)
            return ((Float) o).intValue();
        if(o instanceof Long)
            return ((Long) o).intValue();
        if(o instanceof BigDecimal)
            return ((BigDecimal) o).intValue();
        return ((Integer) o).intValue();
    }

    public static double castObjectDoubleValue(Object o){
        if(o instanceof Float)
            return ((Float) o).doubleValue();
        return ((Double) o).doubleValue();
    }
}
