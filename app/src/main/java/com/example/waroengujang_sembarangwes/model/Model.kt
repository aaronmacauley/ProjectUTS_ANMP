package com.example.waroengujang_sembarangwes.model
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "menu_table")
data class Menu(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val foto: String?,
    val nama: String?,
    val kategori: String?,
    val deskripsi: String?,
    val harga: Int?
)

@Entity(tableName = "order_table")
data class Order(
    @PrimaryKey
    val order_id: String,
    val no_table: Int,
    val harga_total: Int,
    val duration: String,
    val status: Int
)

@Entity(tableName = "waiter_table")
data class Waiter(
    @PrimaryKey
    val username:String,
    val password:String,
    val img_url: String,
    val work_since: String,
    val name: String
)

@Entity(tableName = "order_detail_table",
    foreignKeys = [
        ForeignKey(
            entity = Order::class,
            parentColumns = ["order_id"],
            childColumns = ["order_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class OrderDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val order_id: Int,
    val table: Int,
    val duration: String,
    val total_harga: Int,
    val menu: String
)

@Entity(tableName = "cart_item_table")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val menuItem: Menu,
    var quantity: Int
)
