package com.example.armsapp.ui.viewmodels

import androidx.lifecycle.ViewModel

class ArmsUIViewModel : ViewModel() {

    companion object Urls {
        val logoImageUrls : String = "https://arms.com.br/wp-content/uploads/2024/11/Rectangle-65.png"
    }

    val logoImageUrls: String = Urls.logoImageUrls

}