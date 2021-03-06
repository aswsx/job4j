package ru.alazarev.lsp.storages;

import ru.alazarev.lsp.foods.IFood;

import java.util.Date;
import java.util.List;

/**
 * Class Trash решение задачи части 004. 3.1. Хранилище продуктов [#852]
 *
 * @author Aleksey Lazarev
 * @since 27.05.2019
 */
public class Trash implements IStorage {
    private final Storage storage;

    /**
     * Constructor.
     */
    public Trash() {
        this.storage = new Storage();
        this.storage.setAcceptQualityLower(100);
    }

    /**
     * Method add food into storage.
     *
     * @param food Food value.
     * @return this object.
     */
    @Override
    public boolean addTo(IFood food, Date date) {
        return quality(food, date) ? this.storage.getFoods().add(food) : false;
    }

    /**
     * Method override calculate freshness.
     *
     * @param food Food object for calculate.
     * @param date Date object for calculate.
     * @return Freshness.
     */
    @Override
    public boolean quality(IFood food, Date date) {
        return food.freshness(date) > this.storage.acceptQualityLower;
    }

    /**
     * Method return foods.
     *
     * @return Food list value.
     */
    @Override
    public List<IFood> getFoods() {
        return this.storage.getFoods();
    }

    /**
     * Method clear food list.
     */
    @Override
    public void clearFoods() {
        this.storage.clearFoods();
    }
}
