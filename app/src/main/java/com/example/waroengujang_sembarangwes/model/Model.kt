package com.example.waroengujang_sembarangwes.model
data class Menu(
    val id:Int?,
    val foto:String?,
    val nama:String?,
    val kategori:String?,
    val deskripsi:String?,
    val harga:Int?
)
data class Order(
    val order_id: String,
    val no_table: Int,
    val harga_total: Int,
    val duration: String,
    val status: Int
)
data class Waiter(
    val img_url:String,
    val work_since:String,
    val name:String
)
data class OrderDetail(
    val table:Int,
    val duration:String,
    val total_harga:Int,
    val menu:String
)

data class CartItem(
    val menuItem: Menu,
    var quantity: Int,
)