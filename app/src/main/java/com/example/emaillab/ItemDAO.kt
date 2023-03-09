package com.example.emaillab

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDAO
{
    @Query("SELECT * FROM item_table")
    fun getAll(): Flow <List<ItemEntity>>

    @Query("SELECT price FROM item_table")
    fun getPrice(): Flow <List<String>>

    @Query("SELECT url FROM item_table")
    fun getUrl(): Flow <List<String>>
    /*@Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Item>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Item*/

    @Insert
    fun insertAll(vararg items: ItemEntity)

    @Query("DELETE FROM item_table")
    fun deleteAll()
}