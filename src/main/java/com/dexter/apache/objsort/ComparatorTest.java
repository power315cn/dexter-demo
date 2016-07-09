package com.dexter.apache.objsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;

/** 
 * Java对象的按属性排序
 * @ClassName:	ComparatorTest 
 * @Description:
 * 在日常工作中经常用到需要对java对象集合或者Map集合中的某个属性做排序，
 * 这个需求可以利用Apache的commons-beanutils和commons-collections包来实现
 * @author:	dexter
 * @date:	2016年7月9日 下午2:33:02 
 *  
 */
public class ComparatorTest {

    public static void main(String[] args) throws Exception {
        test1();
        test2();
    }

    private static void test1() {
        List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> user = new LinkedHashMap<String, Object>();
            user.put("username", "user_" + i);
            user.put("password", "passwd_" + i);
            user.put("email", "user_" + i + "@localhost");
            users.add(user);
        }
        System.out.println(users);

        sort(users, "username", false);
        System.out.println(users);

        sort(users, "username", true);
        System.out.println(users);
    }

    private static void test2() {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 5; i++) {
            users.add(new User("user_" + i, "passwd_" + i, "user_" + i + "@localhost"));
        }
        System.out.println(users);

        sort(users, "username", false);
        System.out.println(users);

        sort(users, "username", true);
        System.out.println(users);
    }

    public static <T> void sort(List<T> list, String property, boolean asc) {
        Comparator<?> comparator = ComparableComparator.getInstance();
        comparator = ComparatorUtils.nullLowComparator(comparator);
        if (!asc) {
            comparator = ComparatorUtils.reversedComparator(comparator);
        }
        Collections.sort(list, new BeanComparator(property, comparator));
    }
}