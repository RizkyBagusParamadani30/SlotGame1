package com.example.slotgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Thread t; /* Nama untuk sebuah thread*/
    Handler h; /*Membutuhkan sebuah handler*/
    int id = 1; /*Untuk Gambar Pertama*/
    private ImageView imgSlot;
    private Button btnStarStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imgSlot = this.findViewById(R.id.imgSlot);
        this.btnStarStop = this.findViewById(R.id.btnStartStop);
        this.btnStarStop.setOnClickListener(this);

        this.h = new Handler(Looper.getMainLooper());/*Buat  Object Handler */
    }

    @Override
    public void onClick(View v) { /* Button */
        if (this.t != null && this.t.isAlive()){
            this.t.interrupt();
            return;
        }

        this.t = new Thread(new Runnable() {
            @Override
            public void run() {
                /*Kode Disini dijalankan di Thread terpisah*/
                while (true){
                    if (id==9)id =1; /* Jka id sampai 9 maka jadikan id menjadi 1 */
                    else id++; /* Jika tidak id ++*/
                    try {
                        Thread.sleep(100); /* Tidurkan thread berikut selama 100 ms*/
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                /*Proses berjalan di dalam UI Thread */
                                imgSlot.setImageResource(Helper.getIcon(id));/*Ambil dari helper untuk mengambil gambar*/
                            }
                        });
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
        this.t.start();
    }
}