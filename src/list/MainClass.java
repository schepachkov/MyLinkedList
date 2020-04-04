package list;

import java.util.Arrays;
import java.util.Iterator;


public class MainClass {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        System.out.println("toString - " + list);
        System.out.println("List size " + list.size());
        System.out.println(String.format("Set %d on the 0 ", list.set(0,699)));
        System.out.println("toString - " + list);
        System.out.println("Poll first " + list.pollFirst());
        System.out.println("toString - " + list);
        System.out.println("Poll last " + list.pollLast());
        System.out.println("toString - " + list);
        System.out.println("Remove by index 3, elem = " + list.remove(3));
        System.out.println("toString - " + list);
        Integer[] arr = list.toArray(new Integer[0]);
        System.out.println("Integer array - " + Arrays.toString(arr));
        System.out.println("Object array - " + Arrays.toString(list.toArray()));

        System.out.println("IndexOf(5) = " + list.indexOf(5));
        System.out.println("Lambda foreach loop:");
        list.forEach(System.out::println);

        System.out.println("Iterator remove where elements > 5 :");
        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();){
            Integer elem = iterator.next();
            if (elem > 5) iterator.remove();
        }
        System.out.println(list);

        System.out.println("=================");
        System.out.println("List 1 = " + list);
        System.out.println("Create a new list from list1");
        MyLinkedList<Integer> list2 = new MyLinkedList<>(list);
        System.out.println("List 2 = " + list2);
        System.out.println("=================");

        System.out.println("list.equals(list2) " + list.equals(list2));
        System.out.println("list == list2 " + (list == list2));

        System.out.println("list.hashCode() == list2.hashCode() " + (list.hashCode() == list2.hashCode()));
        System.out.println("Clear list1 and list2");
        list.clear();
        list2.clear();
        System.out.println("List 1 " + list);
        System.out.println("List 2 " + list2);
    }
}
