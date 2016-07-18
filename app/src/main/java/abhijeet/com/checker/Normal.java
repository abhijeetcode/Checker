package abhijeet.com.checker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Normal extends AppCompatActivity {
    int total;
    ByteArrayOutputStream bs;
    int resId[] = {R.drawable.circles, R.drawable.triangle, R.drawable.cylinder, R.drawable.rectangle};
    MediaPlayer mp;
    private static final String FORMAT = "%02d";
    AlertDialog.Builder adb;
    Button matchbutton, unmatchbutton;
    ImageView imageview;
    TextView tvscore, tvtimer;
    Bitmap bitmap1, bitmap2,bitmap3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mp = MediaPlayer.create(this, R.raw.titlescreen);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Id of Widgets
        matchbutton = (Button) findViewById(R.id.imageButton6);
        unmatchbutton = (Button) findViewById(R.id.imageButton7);
        imageview = (ImageView) findViewById(R.id.imageView2);
        tvtimer = (TextView) findViewById(R.id.textView);
        tvscore = (TextView) findViewById(R.id.textView2);
        Intent in = getIntent();
        Random ran = new Random();

        //Randomly image is adding in imageView
        int ind = ran.nextInt(resId.length);
        imageview.setImageResource(resId[ind]);
        bitmap1 = BitmapFactory.decodeResource(this.getResources(), resId[ind]);
        bs = new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.PNG, 50, bs);
        //Countdown Timer
        // adjust the milli seconds here
        new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                //mp.start();
                tvtimer.setText("" + String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            //After finish of timer
            public void onFinish() {
                adb = new AlertDialog.Builder(Normal.this);
                adb.setTitle("Game Over");
                adb.setMessage("Time Out");
                adb.setIcon(R.drawable.tool);
                //mp.stop();
                // Exiting Dialogbox
                adb.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent in = new Intent(Normal.this, Menuscreen.class);
                        startActivity(in);
                    }
                });
                //Exiting Dialogbox
                adb.setNegativeButton("Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent in = new Intent(Normal.this, First.class);
                        startActivity(in);
                    }
                });
                adb.show();
            }
        }.start();
        matchbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Random ran = new Random();
                int ind = ran.nextInt(resId.length);
                bitmap2 = BitmapFactory.decodeByteArray(
                        getIntent().getByteArrayExtra("abc"), 0, getIntent().getByteArrayExtra("abc").length);
                total = getIntent().getIntExtra("def", 0);
                if (bitmap2.sameAs(bitmap1))
                {
                    Toast.makeText(Normal.this, "matched", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(Normal.this, Normal.class);
                    total = total + 1;
                    bs = new ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.PNG, 50, bs);
                    intent2.putExtra("abc",bs.toByteArray());
                    intent2.putExtra("def",total);
                    startActivity(intent2);
                    finish();
                    tvscore.setText("Score"+total);
                }
                else
                {

                   // Toast.makeText(Normal.this, " Not matched", Toast.LENGTH_SHORT).show();
                    adb = new AlertDialog.Builder(Normal.this);
                    adb.setTitle("Game Over");
                    adb.setMessage("Score"+total);
                    adb.setIcon(R.drawable.tool);
                    //mp.stop();
                    // Exiting Dialogbox
                    adb.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent in = new Intent(Normal.this, Menuscreen.class);
                            startActivity(in);
                        }
                    });
                    //Exiting Dialogbox
                    adb.setNegativeButton("Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent in = new Intent(Normal.this, First.class);
                            startActivity(in);
                        }
                    });
                    adb.show();

                }
            }
        });
        unmatchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Random ran = new Random();
                int ind = ran.nextInt(resId.length);
                bitmap3 = BitmapFactory.decodeByteArray(
                        getIntent().getByteArrayExtra("abc"), 0, getIntent().getByteArrayExtra("abc").length);
                total = getIntent().getIntExtra("def", 0);

                if (bitmap3.sameAs(bitmap1))
                {
                    //Toast.makeText(Normal.this, "Not matched", Toast.LENGTH_SHORT).show();
                    adb = new AlertDialog.Builder(Normal.this);
                    adb.setTitle("Game Over");
                    adb.setMessage("Score "+total);
                    adb.setIcon(R.drawable.tool);
                    //mp.stop();
                    // Exiting Dialogbox
                    adb.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent in = new Intent(Normal.this, Menuscreen.class);
                            startActivity(in);
                        }
                    });
                    //Exiting Dialogbox
                    adb.setNegativeButton("Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent in = new Intent(Normal.this, First.class);
                            startActivity(in);
                        }
                    });
                    adb.show();
                }
                else
                {
                   // Toast.makeText(Normal.this, "matched", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(Normal.this, Normal.class);
                    total = total + 1;
                    bs = new ByteArrayOutputStream();
                     bitmap1.compress(Bitmap.CompressFormat.PNG, 50, bs);
                     intent2.putExtra("abc",bs.toByteArray());
                    intent2.putExtra("def",total);
                    startActivity(intent2);
                    finish();
                    tvscore.setText("Score "+total);
                }
            }
        });
    }
}