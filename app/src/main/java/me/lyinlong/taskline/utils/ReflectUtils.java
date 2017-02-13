package me.lyinlong.taskline.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 对象反射工具类
 * Created by ep on 2017/1/2.
 */
public class ReflectUtils {

    /**
     * 获取指定属性的具体值
     * 值类型保留
     * @param obj   对象
     * @param attrs 对象的多个属性名
     * @return  key:属性名   value:对应的值
     */
    public static Map<String , Object > getAttrVal(Object obj, String ... attrs){

        if(obj == null)
            return null;

        Class clazz = obj.getClass();

        Field[] fields = clazz.getDeclaredFields();
        if(fields == null || fields.length == 0)
            return null;

        Map<String , Object> result = new LinkedHashMap<>();

        try {

            // 最终需要进行获取值的属性
            List<Field> consFiled = new ArrayList<>();

            // 转换为list
            List<Field> fieldLists = Arrays.asList(fields);

            // 如果没有自定义获取值的属性 , 那么获取所有属性的值
            if(attrs == null || attrs.length == 0)
//                fieldLists.forEach(f -> consFiled.add(f));
                for (Field field : fieldLists) {
                    consFiled.add(field);
                }
            else{
                List<String> attr_temps = Arrays.asList(attrs);
                for (String attr : attr_temps) {
                    for (Field field : fieldLists) {
                        if(field.getName().equals(attr))
                            consFiled.add(field);
                    }
                }
//                Arrays.asList(attrs).forEach( a ->  consFiled.add(
//                        fieldLists.stream().filter( f -> a.equals(f.getName()) ).findFirst().get()
//                ));
            }

            // 找出满足条件的属性值
            for(Field field : consFiled){

                boolean access = field.isAccessible();
                if(!access) field.setAccessible(true);

                String getterName = toGAStter("get",field.getName());

                result.put(field.getName() , invokeMethod(obj , getterName));

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 获取指定属性的具体值
     * 值类型不保留，都转为String
     * @param obj   对象
     * @param attrs 对象的多个属性名
     * @return  key:属性名   value:对应的值
     */
    public static Map<String, String> getAttrVal_String(Object obj, String ... attrs){
        Map<String, Object> attrVal = getAttrVal(obj, attrs);

        Map<String, String> fields = new LinkedHashMap<>();
        for (String key : attrVal.keySet()) {
            fields.put(key, String.valueOf(attrVal.get(key)));
        }

        return fields;
    }

    /**
     * 获取类中所有public 的属性
     * @param obj
     * @return
     */
    public static Map<String, String> getAttrValByPublic(Object obj){
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        String[] attrs = new String[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            attrs[i] = declaredFields[i].getName();
        }

        return getAttrVal_String(obj, attrs);
    }

    /**
     * 组装成Getter/Setter方法名
     * @param fieldName
     * @return
     */
    public static String toGAStter( String prefix,String fieldName){

        if (fieldName == null || fieldName.length() == 0) {
            return null;
        }

        fieldName = new StringBuffer(prefix).append(fieldName.substring(0, 1).toUpperCase())
                .append(fieldName.substring(1)).toString();

        return  fieldName;
    }

    /**
     * 调用方法
     * @param obj
     * @param methodName
     * @param args
     * @return
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object invokeMethod(Object obj, String methodName, Object ... args)
            throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException {


        if (args == null || args.length == 0 ) {
            // 调用没有参数的方法
            return obj.getClass().getMethod(methodName).invoke(obj);
        } else {

            // 获取每个参数的类型
            Class [] paramTypes = new Class[args.length];

            for (int i = 0; i < args.length; i++) {
                paramTypes[i] = args[i].getClass();
            }

            return obj.getClass().getMethod(methodName, paramTypes ).invoke(obj, args);
        }
    }

    /**
     * 获取所有属性信息
     * @param obj   具体对象
     * @return
     */
    public static List<String> getAllAttrs(Object obj){
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        List<String> attrs = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            attrs.add(declaredField.getName());
        }
        return attrs;
    }

    /**
     * 获取属性类型
     * @param obj   对象
     * @param field 属性
     * @return
     */
    public static Class getFieldType(Object obj, String field){
        try {
            return obj.getClass().getDeclaredField(field).getType();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }
}
