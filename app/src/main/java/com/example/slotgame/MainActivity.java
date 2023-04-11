package com.example.slotgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Thread t1;
    Thread t2;
    Thread t3; /* Nama untuk sebuah thread*/
    Handler h; /*Membutuhkan sebuah handler*/
    int id = 1; /*Untuk Gambar Pertama*/
    private ImageView imgSlot1;
    private Button btnStarStop;
    private ImageView imgSlot2;
    private ImageView imgSlot3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.imgSlot1 = this.findViewById(R.id.imgSlot1);
        this.imgSlot2 = this.findViewById(R.id.imgSlot2);
        this.imgSlot3 = this.findViewById(R.id.imgSlot3);
        this.btnStarStop = this.findViewById(R.id.btnStartStop);
        this.btnStarStop.setOnClickListener(this);

        this.h = new Handler(Looper.getMainLooper());/*Buat  Object Handler */
    }

    @Override
    public void onClick(View v) { /* Button */
        if (t1 != null && t1.isAlive()){
            t1.interrupt();
            return;
        }
        if (t2 != null && t2.isAlive()){
            t2.interrupt();
            return;
        }
        if (t3 != null && t3.isAlive()){
            t3.interrupt();
            return;
        }

        this.t1 = new Thread(new Runnable() {
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
                                imgSlot1.setImageResource(Helper.getIcon(id));/*Ambil dari helper untuk mengambil gambar*/

                            }
                        });
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
        this.t1.start();
        t2 = new Thread(new Runnable() {
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
                                imgSlot2.setImageResource(Helper.getIcon(id));/*Ambil dari helper untuk mengambil gambar*/
                            }
                        });
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
        t2.start();

        t3 = new Thread(new Runnable() {
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
                                imgSlot3.setImageResource(Helper.getIcon(id));/*Ambil dari helper untuk mengambil gambar*/
                            }
                        });
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
        t3.start();
    }
}