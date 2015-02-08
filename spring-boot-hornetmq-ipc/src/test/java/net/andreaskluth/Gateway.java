package net.andreaskluth;

import java.util.ArrayDeque;
import java.util.Deque;

public class Gateway {
    
    private static Deque<Object> STACK = new ArrayDeque<>();
    
    public static void willRespondWith(Object message) {
        STACK.add(message);
    }

    public static Object obtainNextResult() {
        return STACK.pop();
    }

}
