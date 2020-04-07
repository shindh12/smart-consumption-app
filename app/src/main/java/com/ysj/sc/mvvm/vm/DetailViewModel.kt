package com.ysj.sc.mvvm.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ysj.sc.mvvm.db.SCDatabase
import com.ysj.sc.mvvm.db.entity.Consumption

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val db: SCDatabase = SCDatabase.getInstance(application)

    fun getConsumptions(): LiveData<List<Consumption>> {
        return db.consumptionDao().getAll()
    }

    fun getDetailsByCategory(category: String): List<Consumption> {
        return db.consumptionDao().getDetailsByCategory(category)
    }
}