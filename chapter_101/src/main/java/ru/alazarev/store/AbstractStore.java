package ru.alazarev.store;

import ru.alazarev.generic.SimpleArray;

/**
 * Class AbstractStore решение задачи части 001. Урок 5.2.2. Реализовать Store<T extends Base> [#157].
 *
 * @author Aleksey Lazarev
 * @since 04.12.2018
 */
public class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> models;
    private int size;
    private int countElements = 0;

    /**
     * Constructor.
     *
     * @param size Array size.
     */
    public AbstractStore(int size) {
        this.size = size;
        models = new SimpleArray(this.size);
    }

    public int getCountElements() {
        return countElements;
    }

    /**
     * Add new element method.
     *
     * @param model Element to add.
     */
    public void add(T model) {
        models.add(model);
        countElements++;
    }

    /**
     * Method replaces element with id.
     *
     * @param id    Element id.
     * @param model Replacement item.
     * @return Result of replaces.
     */
    public boolean replace(String id, T model) {
        boolean result = false;
        int findItem = findIndexById(id);
        if (findItem != -1) {
            models.set(findItem, model);
            result = true;
        }
        return result;
    }

    /**
     * Method delete item from array.
     *
     * @param id
     * @return
     */
    public boolean delete(String id) {
        boolean result = false;
        int index = findIndexById(id);
        if (index != -1) {
            models.delete(index);
            result = true;
            countElements--;
        }
        return result;
    }

    /**
     * Method find element by his id.
     *
     * @param id Element required id.
     * @return Find element.
     */
    public T findById(String id) {
        int index = findIndexById(id);
        return index != -1 ? models.get(index) : null;
    }

    /**
     * Method find index element by his id.
     *
     * @param id Element required id.
     * @return Index element.
     */
    public int findIndexById(String id) {
        int result = -1;
        int index = 0;
        while (index < countElements) {
            if (models.get(index) != null && models.get(index).getId().equals(id)) {
                result = index;
                break;
            }
            index++;
        }
        return result;
    }
}
