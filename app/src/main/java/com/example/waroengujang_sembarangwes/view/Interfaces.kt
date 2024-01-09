package com.example.waroengujang_sembarangwes.view

import android.view.View

interface SubmitTableNumberListener{
    fun onSubmitTableNumberListener(v: View)
}

interface ProcessToKitchenListener{
    fun onProcessToKitchenListener(v: View)
}

interface AddMenuToOrderListener{
    fun onAddMenuToOrderListener(v: View)
}

interface MenuDetailListener{
    fun onMenuDetailListener(v: View)
}

interface OrderDetailListener{
    fun onOrderDetailListener(v: View)
}

interface CloseBillListener{
    fun onCloseBillListener(v: View)
}