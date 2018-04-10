package bagusandinata.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    ProgressDialog progressDialog;
    String[] items = {"Easy", "Medium", "Hard", "Very Hard"};
    String results = "";
    Handler handler;
    Runnable runnable;
    Timer timer;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //alert dialog
//        getAlertDialog();

        //confirmation dialog
//        getConfirmationDialog();

        //progress dialog
        getProgressDialog();


    }

    private void getAlertDialog(){
        //if you change theme button please create R.style.blabla
        builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogTheme);

        //message dialog
        builder.setMessage("Discard draft");

        //set positive button
        builder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "discard", Toast.LENGTH_SHORT).show();
            }
        });

        //set negative button
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
            }
        });

        //create dialog
        alertDialog = builder.create();

        //show dialog
        alertDialog.show();

        //set color positive button
        //alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorAlertDialog));
    }

    private void getConfirmationDialog(){
        //if you change theme button please create R.style.blabla
        builder = new AlertDialog.Builder(MainActivity.this, R.style.ConfirmationDialogTheme);

        //set title dialog
        builder.setTitle("Select the difficulty level");

        //set choice dialog
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                results = items[which];
            }
        });

        //set positive button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), results, Toast.LENGTH_SHORT).show();
            }
        });

        //set negative button
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "CANCEL", Toast.LENGTH_SHORT).show();
            }
        });

        //create dialog
        alertDialog = builder.create();

        //show dialog
        alertDialog.show();
    }

    private void getProgressDialog(){

        progressDialog = new ProgressDialog(MainActivity.this);

        //style horizontal progress dialog
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        //set style dialog
        progressDialog.setIndeterminate(false);

        //set title progress dialog
        progressDialog.setTitle("Progress Dialog");

        //set message progress dialog
        progressDialog.setMessage("Please wait...");

        //progress dialog show
        progressDialog.show();

        //set progress
        progressDialog.setProgress(0);

        //set max progress
        progressDialog.setMax(100);

        //create handler
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                i = i+5;
                if (i<=100){

                    progressDialog.setProgress(i);

                }else {

                    timer.cancel();
                    progressDialog.cancel();
                    i=0;
                }
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },3000, 100);

    }

}
