package com.ysj;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ysj.sc.R;
import com.ysj.sc.databinding.ActivityMainBinding;
import com.ysj.sc.databinding.CustomActionbarBinding;
import com.ysj.sc.layout.fragment.AnalysisFragment;
import com.ysj.sc.layout.fragment.CategoryFragment;
import com.ysj.sc.layout.fragment.ConsumptionFragment;
import com.ysj.sc.mvvm.vm.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    ActivityMainBinding binding;
    CustomActionbarBinding actionbarBinding;
    MainViewModel viewModel;

    private CategoryFragment categoryFragment;
    private AnalysisFragment analysisFragment;
    private ConsumptionFragment consumptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        categoryFragment = new CategoryFragment();
        analysisFragment = new AnalysisFragment();
        consumptionFragment = new ConsumptionFragment();

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.navigation_consumption, R.id.navigation_analysis, R.id.navigation_category).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setBottomNavigation();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        setCustomActionBar();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        String lastDate = viewModel.getLastDate();
        // 최근 날짜도 없으면 Data 비어있으므로 생성
        if (lastDate == null) {
            viewModel.getAllDataFromServer("jinny5025");
        }else if (viewModel.checkLastDateIsNotToday(lastDate)) {
            // 최근 날짜 있는데 오늘이 아니면 그 날짜 기준으로 요청
            // 그리고 이 때 Statistics 업데이트 함
            // TODO: 2020-03-31 lastDate 기준으로 Data 가져오기
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private void setBottomNavigation() {
        binding.navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_consumption:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, consumptionFragment).commit();
                    break;
                case R.id.navigation_analysis:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, analysisFragment).commit();
                    break;
                case R.id.navigation_category:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, categoryFragment).commit();
                    break;
                default:
                    Log.d(TAG, "This id is " + item.getItemId() + ", and consumption is " + R.id.navigation_consumption);
                    break;
            }
            return true;
        });

    }


    private void setCustomActionBar() {
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        View customActionBar = LayoutInflater.from(this).inflate(R.layout.custom_actionbar, null);
        actionbarBinding = DataBindingUtil.bind(customActionBar);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setBackgroundDrawable(new ColorDrawable(getColor(R.color.app_background)));
        actionBar.setCustomView(customActionBar, params);
        actionBar.setElevation(0);


        Toolbar parent = (Toolbar) customActionBar.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        actionbarBinding.ivActionBarBack.setOnClickListener(v -> {
            Log.d("###", "back!!");
            getSupportFragmentManager().popBackStack();
        });
    }

    public void toggleVisibleBackButton(boolean isVisible) {
        if (isVisible) {
            actionbarBinding.ivActionBarBack.setVisibility(View.VISIBLE);
        } else {
            actionbarBinding.ivActionBarBack.setVisibility(View.GONE);
        }
    }
}
