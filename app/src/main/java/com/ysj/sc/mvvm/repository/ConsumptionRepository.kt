package com.ysj.sc.mvvm.repository

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.ysj.sc.adapter.entity.ConsumptionVO
import com.ysj.sc.mvvm.db.SCDatabase
import com.ysj.sc.mvvm.db.dao.ConsumptionDao
import com.ysj.sc.mvvm.db.entity.Consumption
import com.ysj.sc.mvvm.db.entity.Statistics
import com.ysj.sc.mvvm.retrofit.BackendService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConsumptionRepository(application: Application) {
    private val backendService: BackendService by lazy {
        BackendService.instance()
    }
    private val consumptionDao: ConsumptionDao by lazy {
        val db = SCDatabase.getInstance(application)
        db.consumptionDao()
    }

    companion object {
        private var instance: ConsumptionRepository? = null
        fun getInstance(application: Application) : ConsumptionRepository{
            return instance ?: synchronized(this) {
                instance ?: ConsumptionRepository(application)
            }
        }
    }


//    fun getCategoryData() : List<Statistics> {
//        return consumptionDao.getAggregationByCategory()
//    }
    fun getAll() : LiveData<List<Consumption>> {
        return consumptionDao.getAll()
    }
    fun insert(consumptions: List<Consumption>) {
        consumptionDao.insert(consumptions)
    }
    fun getAllDataFromServer(userId: String?) {
        backendService.getAllDetailConsumption(userId).enqueue(object : Callback<List<Consumption>> {
            override fun onFailure(call: Call<List<Consumption>>, t: Throwable) {
                Log.d("####", "Failed : " + t.localizedMessage)
            }
            override fun onResponse(call: Call<List<Consumption>>, response: Response<List<Consumption>>) {
                consumptionDao.insert(response.body()!!)
                Log.d("####", "insert..." + response.body()!!.size)
                // 여기서 insert가 비동기라서 category data가 0건 처리됨 어케하지, DB Trigger 일단 찾아보다 간다
            }
        })
    }
}
