package haxidenti.duckLib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Duck {
    public static <T> T quack(Class<T> clazz, Object o) {
        try {
            if (Proxy.isProxyClass(o.getClass())) {
                var handler = Proxy.getInvocationHandler(o);
                if (handler instanceof Handler) {
                    o = ((Handler) handler).getObjectInside();
                }
            }
            return (T) Proxy.newProxyInstance(o.getClass().getClassLoader(), new Class[]{clazz}, new Handler(o));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class Handler implements InvocationHandler {
        private Object object;
        private Class clazz;

        public Handler(Object object) {
            this.object = object;
            this.clazz = object.getClass();
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                if (method.getName().equals("data")) return object;
                Method m = clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());
                return m.invoke(object, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public Object getObjectInside() {
            return object;
        }
    }
}
