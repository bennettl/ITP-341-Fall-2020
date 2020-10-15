package itp341.lee.coffeeshop.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = CoffeeShop.class, exportSchema = false, version = 1)
public abstract class CoffeeShopDatabase extends RoomDatabase {

    public abstract CoffeeShopDao coffeeShopDao();

}
