package com.example.waroengujang_sembarangwes.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WaroengUjangDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg menu: Menu)

    @Query("SELECT * FROM menu_table")
    fun selectAllMenu(): List<Menu>

    @Query("SELECT * FROM menu_table WHERE id= :id")
    fun selectTodo(id:Int): Menu

    @Delete
    fun deleteMenu(todo:Menu)



}