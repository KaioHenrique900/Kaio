package com.example.kaio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.kaio.fragment.BibliotecaViewFragment;
import com.example.kaio.fragment.HomeViewFragment;
import com.example.kaio.fragment.TopPiadasViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*final String email = Config.getLogin(HomeActivity.this);
        final String password = Config.getPassword(HomeActivity.this);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "get_data.php", "GET", "UTF-8");
                httpRequest.setBasicAuth(email, password);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {
                        final String webData = jsonObject.getString("data");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                /*TextView tvWebData = findViewById(R.id.tvWebData);
                                tvWebData.setText(webData);
                            }
                        });

                    }
                    else {
                        final String error = jsonObject.getString("error");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HomeActivity.this, error, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });*/

        HomeViewFragment homeViewFragment = new HomeViewFragment();
        setFragment(homeViewFragment);

        bottomNavigationView = findViewById(R.id.btNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.HomeViewOp:
                        HomeViewFragment homeViewFragment = HomeViewFragment.newInstance();
                        setFragment(homeViewFragment);
                        break;
                    case R.id.bibliotecaViewOp:
                        BibliotecaViewFragment bibliotecaViewFragment = BibliotecaViewFragment.newInstance();
                        setFragment(bibliotecaViewFragment);
                        break;
                    case R.id.topPiadasViewOp:
                        TopPiadasViewFragment topPiadasViewFragment = TopPiadasViewFragment.newInstance();
                        setFragment(topPiadasViewFragment);
                        break;
                }
                return true;
            }
        });

        ImageView imUser = findViewById(R.id.imUser);
        imUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, PerfilUserActivity.class);
                startActivity(i);
            }
        });

    }

    void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}