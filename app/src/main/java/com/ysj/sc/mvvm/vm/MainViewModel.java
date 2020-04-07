package com.ysj.sc.mvvm.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ysj.sc.mvvm.db.SCDatabase;
import com.ysj.sc.mvvm.repository.ConsumptionRepository;
import com.ysj.sc.mvvm.repository.StatisticsRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainViewModel extends AndroidViewModel {
    private static final String TODAY = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
    private SCDatabase db;
    private ConsumptionRepository cRepository;
    private StatisticsRepository sRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        db = SCDatabase.Companion.getInstance(application);
        cRepository = ConsumptionRepository.Companion.getInstance(application);
        sRepository = StatisticsRepository.Companion.getInstance(application);
    }

    public boolean checkLastDateIsNotToday(String lastDate) {
        return !lastDate.equals(TODAY);
    }

    public String getLastDate() {
        return db.consumptionDao().getLastDate();
    }

    public void getAllDataFromServer(String userId) {
        cRepository.getAllDataFromServer(userId);
    }

    public void getAllStatisticsData(String userId) {
        sRepository.getAllChartData();
    }

}
