package com.example.waroengujang_sembarangwes.view

import android.view.View

interface submitTableNumberListener{
    fun onSubmitTableNumberListener(v: View)
}

interface processToKitchenListener{
    fun onProcessToKitchenListener(v: View)
}

interface addMenuToOrderListener{
    fun onAddMenuToOrderListener(v: View)
}

interface menuDetailListener{
    fun onMenuDetailListener(v: View)
}

interface orderDetailListener{
    fun onOrderDetailListener(v: View)
}

interface closeBillListener{
    fun onCloseBillListener(v: View)
}