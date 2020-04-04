package list.speedtest;

import list.MyLinkedList;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class SpeedTest {
    public static void main(String[] args) {
        MyLinkedList<Integer> myList = new MyLinkedList<>();
        List<Integer> list = new LinkedList<>();
        addTest(myList, list, 1_000_000);
        getTest(myList, list, 534_212);
        writeResults("================================================");

    }

    private static void addTest(MyLinkedList<Integer> myList, List<Integer> list, int count) {
        long date1 = System.nanoTime();
        for (int i = 0; i < count; i++) {
            myList.add(i);
        }
        long date2 = System.nanoTime();
        writeResults(String.format("Add %d elements",count), "Time for my LinkedList = " + (date2 - date1));
        date1 = System.nanoTime();
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        date2 = System.nanoTime();
        writeResults("Time for Java's LinkedList - " + (date2 - date1) + "\n");
    }

    private static void getTest(MyLinkedList<Integer> myList, List<Integer> list, int index) {
        long date1 = System.nanoTime();
        int elem = myList.get(index);
        long date2 = System.nanoTime();
        writeResults(String.format("Get elem by index. Index = %d",index), "MyList have worked for " + (date2 - date1) + ", element = " + elem);
        date1 = System.nanoTime();
        elem = list.get(index);
        date2 = System.nanoTime();
        writeResults("Java's linked list have worked for " + (date2 - date1) + ", element = " + elem);
    }


    private static void writeResults(String... lines){
        try(BufferedWriter bufferedOutputStream = new BufferedWriter(new FileWriter(new File("src/list/speedtest/results.txt"),true))){
            for (int i = 0; i < lines.length; i++) {
                bufferedOutputStream.write(lines[i] + "\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
