package com.teratail.q_6iqd6y0eob59di;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.*;

import android.os.Bundle;

import java.time.DayOfWeek;

public class MainActivity extends AppCompatActivity {
  private ListFragmentViewModel viewmodel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    viewmodel = new ViewModelProvider(this).get(ListFragmentViewModel.class);
    if(savedInstanceState == null) { //初回起動
      //テストデータ
      viewmodel.regist(new Schedule(DayOfWeek.MONDAY, 0, "A", "AAA"));
      viewmodel.regist(new Schedule(DayOfWeek.MONDAY, 6, "B", "BBB"));
      viewmodel.regist(new Schedule(DayOfWeek.TUESDAY, 12, "C", "CCC"));
      viewmodel.regist(new Schedule(DayOfWeek.FRIDAY, 17, "D", "DDD"));
      viewmodel.regist(new Schedule(DayOfWeek.SATURDAY, 23, "E", "EEE"));

      transList();
    }
  }

  void transList() {
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, new ListFragment())
            .commit();
  }
  void editSchedule(Schedule schedule) {
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, EditFragment.newInstance(schedule))
            .addToBackStack(null)
            .commit();
  }
  void regist(Schedule schedule) {
    viewmodel.regist(schedule);
    getSupportFragmentManager().popBackStack();
  }
  void cancelEdit() {
    getSupportFragmentManager().popBackStack();
  }
}