package model;

public interface CRUD<E> {
    E getById(int id);
    E add(E e);
    void update (E e);
    E deleteById(int id);
    void displayById(int id);
    void displayAll();
}
