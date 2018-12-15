package haxidenti.duckLib;

import java.lang.annotation.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Duck {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Rename {
        String value();
    }


    public static <T> T quack(Class<T> duckClass, Object o) {
        try {
            if (Proxy.isProxyClass(o.getClass())) {
                var handler = Proxy.getInvocationHandler(o);
                if (handler instanceof Handler) {
                    o = ((Handler) handler).getObjectInside();
                }
            }
            return (T) Proxy.newProxyInstance(o.getClass().getClassLoader(), new Class[]{duckClass}, new Handler(o, duckClass));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class Handler implements InvocationHandler {
        private Object object;
        private Class clazz;
        private Class duckClass;

        public Handler(Object object, Class<?> duckClass) {
            this.object = object;
            this.clazz = object.getClass();
            this.duckClass = duckClass;
        }

        public Object invoke(Object proxy, Method callableMethod, Object[] args) throws Throwable {
            try {
                String methodName = callableMethod.getName();
                if (methodName.equals("data") && callableMethod.getParameterTypes().length < 1) {
                    return object;
                }

                Method duckMethod = duckClass.getDeclaredMethod(methodName, callableMethod.getParameterTypes());

                // Rename it, if there is an annotation to do that
                Annotation renameDuckMethodAnnotation = duckMethod.getAnnotation(Rename.class);
                if (renameDuckMethodAnnotation != null) {
                    methodName = ((Rename) renameDuckMethodAnnotation).value();
                }

                Method proxyMethod = clazz.getDeclaredMethod(methodName, callableMethod.getParameterTypes());
                return proxyMethod.invoke(object, args);
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
