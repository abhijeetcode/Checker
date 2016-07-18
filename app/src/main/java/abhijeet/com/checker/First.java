package abhijeet.com.checker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

public class First extends AppCompatActivity
{
    ImageButton gobutton;
    ImageView imageview;
    Bitmap bitmap;
    ByteArrayOutputStream bs;
    int resId[]={R.drawable.circles, R.drawable.triangle, R.drawable.cylinder, R.drawable.rectangle};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab =(FloatingActionButton) findViewById(R.id.fab);
        gobutton=(ImageButton)findViewById(R.id.imageButton5);
        imageview=(ImageView)findViewById(R.id.imageView);
        Random ran=new Random();
        int index = ran.nextInt((resId.length));
        int ID=imageview.getId();

        //Randomly image is adding in imageView
        imageview.setImageResource(resId[index]);
        bitmap= BitmapFactory.decodeResource(this.getResources(),resId[index]);
        bs=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,50,bs);
        gobutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Vibrator vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(100);
                int count=0;
                Intent intent=new Intent(getApplicationContext(),Normal.class);
                intent.putExtra("abc",bs.toByteArray());
                intent.putExtra("def",count);
                startActivity(intent);
            }
        });
    }
}
