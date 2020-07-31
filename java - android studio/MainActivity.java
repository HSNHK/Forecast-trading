package com.projectshsn.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
public FrameLayout Frame_main;
public LinearLayout Main_layout,show_layout;
public TextView txtString,show_len_record,show_time,show_status;
public ImageView img_status;
public String url = "http://192.168.137.1:5000/get";
public String postUrl = "http://192.168.137.1:5000/add";
public String Data1,Data2,Data3,Data4;
public RadioButton A_1,A_2,A_3,B_1,B_2,B_3,C_1,C_2,C_3,D_1,D_2,D_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Main_layout=(LinearLayout)findViewById(R.id.main_layout);
        show_layout=(LinearLayout)findViewById(R.id.show_layout);
        Frame_main=(FrameLayout)findViewById(R.id.frame_main);
        txtString=(TextView)findViewById(R.id.txttest) ;

        show_len_record=(TextView)findViewById(R.id.text_record_count);
        show_status=(TextView)findViewById(R.id.text_status);
        show_time=(TextView)findViewById(R.id.text_last_item);
        img_status=(ImageView)findViewById(R.id.img_status);

        A_1=(RadioButton)findViewById(R.id.A_1);
        A_2=(RadioButton)findViewById(R.id.A_2);
        A_3=(RadioButton)findViewById(R.id.A_3);

        B_1=(RadioButton)findViewById(R.id.B_1);
        B_2=(RadioButton)findViewById(R.id.B_2);
        B_3=(RadioButton)findViewById(R.id.B_3);

        C_1=(RadioButton)findViewById(R.id.C_1);
        C_2=(RadioButton)findViewById(R.id.C_2);
        C_3=(RadioButton)findViewById(R.id.C_3);

        D_1=(RadioButton)findViewById(R.id.D_1);
        D_2=(RadioButton)findViewById(R.id.D_2);
        D_3=(RadioButton)findViewById(R.id.D_3);


        SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("Home", R.drawable.start));
        spaceNavigationView.addSpaceItem(new SpaceItem("Forecast", R.drawable.start));
        spaceNavigationView.showTextOnly();
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Has this deal been profitable ?");
                builder1.setCancelable(true);
                if (A_1.isChecked()){
                    Data1="1";
                }
                if (A_2.isChecked()){
                    Data1="2";
                }
                if (A_3.isChecked()){
                    Data1="3";
                }

                if (B_1.isChecked()){
                    Data2="1";
                }
                if (B_2.isChecked()){
                    Data2="2";
                }
                if (B_3.isChecked()){
                    Data2="3";
                }


                if (C_1.isChecked()){
                    Data3="1";
                }
                if (C_2.isChecked()){
                    Data3="2";
                }
                if (C_3.isChecked()){
                    Data3="3";
                }


                if (D_1.isChecked()){
                    Data4="1";
                }
                if (D_2.isChecked()){
                    Data4="2";
                }
                if (D_3.isChecked()){
                    Data4="3";
                }
                builder1.setPositiveButton(
                        "Yes",

                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String postBody = "{\n\t\"data1\":\""+Data1+"\",\n\t\"data2\":\""+Data2+"\",\n\t\"data3\":\""+Data3+"\",\n\t\"data4\":\""+Data4+"\",\n\t\"data5\":\"1\"\n}";
                                try {
                                    postRequest(postUrl,postBody);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String postBody = "{\n\t\"data1\":\""+Data1+"\",\n\t\"data2\":\""+Data2+"\",\n\t\"data3\":\""+Data3+"\",\n\t\"data4\":\""+Data4+"\",\n\t\"data5\":\"0\"\n}";
                                try {
                                    postRequest(postUrl,postBody);
                                    Toast.makeText(MainActivity.this, "ok send to server", Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this, "not send to server", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                if (itemIndex==1) {
                    try {
                        run();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Main_layout.setVisibility(LinearLayout.GONE);
                    show_layout.setVisibility(LinearLayout.VISIBLE);
                    Frame_main.setBackgroundResource(R.drawable.backshow);
                }
                else {
                    Main_layout.setVisibility(LinearLayout.VISIBLE);
                    show_layout.setVisibility(LinearLayout.GONE);
                    Frame_main.setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.xml.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mu1:
                try {
                    Toast.makeText(this, "Programer Hasan Mohammadi", Toast.LENGTH_SHORT).show();
                    Intent URL = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/HasanH_k"));
                    startActivity(URL);
                } catch (Exception e) {
                    Toast.makeText(this, " Please install a web browser", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
            case R.id.mu2:
                try {
                    finish();
                } catch (Exception e) {
                    Toast.makeText(this, " Please install a web browser", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                break;
        }
        return true;}

    void postRequest(String postUrl, String postBody) throws IOException {

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, postBody);

        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("TAG", response.body().string());
            }
        });
    }

    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(myResponse);

                            show_len_record.setText("Number of records : "+json.getString("len"));
                            show_time.setText("Last Item : "+json.getString("time"));
                            if (json.getString("status").equals("0")){
                                show_status.setText("Status : Bad");
                                img_status.setImageResource(R.drawable.warning);
                            }
                            else{
                                show_status.setText("Status : Good");
                                img_status.setImageResource(R.drawable.statusok);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }
}
