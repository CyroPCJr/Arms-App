package com.example.armsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.armsapp.data.listProjects

class ArmsUIViewModel : ViewModel() {

    companion object Urls {
        const val URL_LOGO_IMAGE : String = "https://arms.com.br/wp-content/uploads/2024/11/Rectangle-65.png"
    }

    fun getAllProjects() = listProjects

    fun getMainProjects() = listProjects.dropLast(4)

}