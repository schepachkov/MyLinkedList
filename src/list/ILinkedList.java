package list;

public interface ILinkedList<E> extends Iterable<E> {

    void add(E elem);
    void add(int index, E elem);
    void clear();
    E get(int index);
    int indexOf(E elem);
    E remove (int index);
    E set(int index, E elem);
    int size();
    Object[] toArray();
    E[] toArray(E[] a);
    String toString();

    E pollFirst();
    E pollLast();

}
