package list;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

public class MyLinkedList<E> implements ILinkedList<E>{

    private int size;
    private Node<E> first;
    private Node<E> last;


    public MyLinkedList(MyLinkedList<E> list) {
        first = new Node<>(list.first.item);
        Node<E> leftNode = first;
        for (int i = 1; i < list.size-1; i++) {
            Node<E> cur = new Node<>(list.get(i));
            leftNode.next = cur;
            cur.prev = leftNode;
            leftNode = cur;
        }
        last = new Node<>(list.last.item);
        leftNode.next = last;
        last.prev = leftNode;
    }

    public MyLinkedList() {
    }

    @Override
    public void add(E elem) {
        Node<E> node = new Node<>(elem);
        if (first == null) {
            first = node;
            last = node;
        }
        else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        size++;
    }



    @Override
    public void add(int index, E elem) {
        Node<E> leftNode = getNode(index - 1);
        Node<E> rightNode = getNode(index);
        Node<E> added = new Node<>(elem);
        leftNode.next = added;
        added.prev = leftNode;
        added.next = rightNode;
        rightNode.prev = added;
        size++;
    }

    @Override
    public void clear() {
        for (Node<E> x = first; x != null;){
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return getNode(index).item;
    }

    @Override
    public int indexOf(E elem) {
        for (int i = 0; i < size; i++) {
            if (getNode(i).item.equals(elem)) return i;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        if (index == 0) return pollFirst();
        if (index >= size - 1) return pollLast();
        Node<E> current = getNode(index);
        Node<E> leftNode = current.prev;
        Node<E> rightNode = current.next;
        leftNode.next = rightNode;
        rightNode.prev = leftNode;
        current.prev = null;
        current.next = null;
        size--;
        return current.item;
    }

    @Override
    public E pollFirst(){
        E item = first.item;
        first = getNode(1);
        first.prev = null;
        size--;
        return item;
    }

    @Override
    public E pollLast(){
        E item = last.item;
        last = getNode(this.size-2);
        last.next = null;
        size--;
        return item;
    }

    @Override
    public E set(int index, E elem) {
        getNode(index).item = elem;
        return elem;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = get(i);
        }
        return arr;
    }

    @Override
    public E[] toArray(E[] a) {
        if (a.length != size) a = (E[]) Array.newInstance(a.getClass().getComponentType(),size);
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            a[i++] = x.item;
        return a;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>(this);
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Consumer<E> consumer = action::accept;
        for (int i = 0; i < size; i++) {
            consumer.accept(getNode(i).item);
        }
    }


    private Node<E> getNode(int index) {
        Node<E> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public String toString() {
        String res = "[";
        for (E elem : this) {
            res += elem + ", ";
        }
        if (res.length() > 2) res = res.substring(0,res.length()-2);
        return res + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLinkedList<?> that = (MyLinkedList<?>) o;
        for (int i = 0; i < this.size(); i++) {
            if (!(that.getNode(i).equals(this.getNode(i)))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first.item, last.item);
    }


    private static class Node<E>{
        private E item;
        private Node<E> next;
        private Node<E> prev;

        public Node(E item) {
            this.item = item;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return item.equals(node.item);
        }

        @Override
        public int hashCode() {
            return Objects.hash(item);
        }
    }

    private static class MyIterator<E> implements Iterator<E>{
        private Node<E> current;
        private MyLinkedList<E> list;
        private int index;
        public MyIterator(MyLinkedList<E> list) {
            this.list = list;
            current = list.first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E data = current.item;
            current = current.next;
            index++;
            return data;
        }

        @Override
        public void remove() {
            list.remove(--index);
        }
    }
}
