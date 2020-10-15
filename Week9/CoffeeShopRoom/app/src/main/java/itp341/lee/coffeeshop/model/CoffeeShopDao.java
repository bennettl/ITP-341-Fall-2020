package itp341.lee.coffeeshop.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CoffeeShopDao {

    @Query("SELECT * FROM coffee_shops")
    List<CoffeeShop> getCoffeeShops();

    @Query("SELECT * FROM coffee_shops WHERE id = :id")
    CoffeeShop findById(long id);

    @Insert
    void insert(CoffeeShop coffeeShop);

    @Update
    void update(CoffeeShop coffeeShop);

    @Delete
    void delete(CoffeeShop coffeeShop);
}
