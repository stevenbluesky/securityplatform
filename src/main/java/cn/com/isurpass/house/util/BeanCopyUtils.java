package cn.com.isurpass.house.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>Description:将一个对象的属性复制到另一个个对象中。<p>
 * <p>要求：1.两个必须要有Getter/Setter方法；2.要复制的属性名称要相似，采用 lowerCamelCase 命名法（相同或者有一定格式: name -> name | name -> parentName）</p>
 *
 * @Author ZHUL
 * @Date: 2018/3/27
 * @Time: 21:59
 */
public class BeanCopyUtils {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        Student student = new Student();
//        Teacher teacher = new Teacher();
//        student.setAge(17);
//        student.setName("name");
//        student.setTitle("title");
////        copyProperties(student, teacher, null);
////        copyProperties(student, teacher, "parent");
//        copyProperties("parent", student, teacher);
//        copyProperties(student, teacher);
//        System.out.println(student);
//        System.out.println(teacher);
    }

    /**
     * 同属性名对象之间的复制，从class1到class2
     * name -> name
     *
     * @param class1
     * @param class2
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyProperties(Object class1, Object class2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        copyProperties(null, class1, null, class2);
    }

    /**
     * parentName -> name
     *
     * @param class1
     * @param class2
     * @param str
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyProperties(Object class1, String str, Object class2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        copyProperties(class1, str, class2);
    }

    /**
     * name->parentName
     *
     * @param class1
     * @param str
     * @param class2
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyProperties(String str, Object class1, Object class2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        copyProperties(str, class1, null, class2);
    }

    /**
     * 要赋值的对象属性可以有 lowerCamelCase 命名规则的前缀
     *
     * @param str1
     * @param class1
     * @param str2
     * @param class2
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static void copyProperties(String str1, Object class1, String str2, Object class2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] filed1 = class1.getClass().getDeclaredFields();
        Field[] filed2 = class2.getClass().getDeclaredFields();
        for (int i = 0; i < filed1.length; i++) {
            String name1 = filed1[i].getName();
            name1 = name1.substring(0, 1).toUpperCase() + name1.substring(1); //Name
            Method m1 = class1.getClass().getMethod("get" + name1);
            Object value = m1.invoke(class1);
            if (str1 != null) {
                name1 = str1 + name1;//parentName
            }
            for (int j = 0; j < filed2.length; j++) {
                String name2 = filed2[j].getName();//name
                if (str2 != null) {//name -> parentName
                    name2 = str2 + name2.substring(0, 1).toUpperCase() + name2.substring(1);
                }else if(str1 == null && str2 == null){
                    name2 = name2.substring(0, 1).toUpperCase() + name2.substring(1);
                }
                if (name1.equals(name2)) {
                    Field f = filed2[j];
                    f.setAccessible(true);
                    f.set(class2, value);
                }
            }
        }//parentName -> name
    }
}
