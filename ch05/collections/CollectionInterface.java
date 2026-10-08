package ch05.collections;

public interface CollectionInterface<T>
{
  boolean add(T element);
  boolean remove(T target);
  boolean contains(T target);
  T get(T target);
  boolean isFull();
  boolean isEmpty();
  int size();
}
