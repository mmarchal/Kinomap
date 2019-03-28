package com.example.testkinomap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> id_vehicle;
    ArrayList<String> name_vehicle;
    ArrayAdapter<String> arrayAdapter;
    Retrofit retrofitData;

    ListView lView;
    ImageView imageVehicle;
    TextView textVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lView = findViewById(R.id.listMain);

        retrofitData = new Retrofit.Builder()
                .baseUrl(RetrofitInterface.URL_DATAS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface rInter = retrofitData.create(RetrofitInterface.class);

        rInter.getInfos().enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                Gson g = new Gson();
                Object test = g.toJson(response.body());
                lectureJson(test);

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

                if (t instanceof IOException) {
                    Toast.makeText(MainActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    void lectureJson(Object data) {

        Gson gson = new Gson();

        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(data));
            JSONObject json2 = jsonObject.getJSONObject("vehicleList");
            JSONArray values = json2.getJSONArray("response");

            for (int i = 0; i<values.length(); i++) {

                JSONObject nameValue = values.getJSONObject(i);
                id_vehicle.add(nameValue.getInt("id"));
                name_vehicle.add(nameValue.getString("name"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
            e.getMessage();
        }

    }
}
