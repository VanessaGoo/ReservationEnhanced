package sg.edu.rp.c346.reservation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText phone;
    EditText size;
    EditText date;
    EditText time;
    CheckBox smoke;
    Button btnReserve;
    Button btnReset;

    String checked = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        name = findViewById(R.id.editTextName);
        phone = findViewById(R.id.editTextPhone);
        size = findViewById(R.id.editTextSize);
        date = findViewById(R.id.editTextDate);
        time = findViewById(R.id.editTextTime);
        btnReserve = findViewById(R.id.buttonReserve);
        btnReset = findViewById(R.id.buttonReset);
        smoke = findViewById(R.id.checkBox);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText("Date: " + dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                    }
                };
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int monthOfYear = c.get(Calendar.MONTH);
                int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,myDateListener,year,monthOfYear,dayOfMonth);
                myDateDialog.show();
            }
        });
        time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Create the Listener to set the time
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time.setText(hourOfDay+":"+minute);

                    }
                };
                Calendar currentTime = Calendar.getInstance();
                int currenthour = currentTime.get(Calendar.HOUR_OF_DAY);
                int currentminute = currentTime.get(Calendar.MINUTE);

                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,myTimeListener,currenthour,currentminute,true);
                myTimeDialog.show();
            }
        });
        smoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(smoke.isChecked()){
                    checked = "Smoking: Yes";
                }
                else{
                    checked = "Smoking: No";
                }
            }
        });
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.input, null);

                final TextView tvName = viewDialog.findViewById(R.id.textViewName);
                final TextView tvSmoking = viewDialog.findViewById(R.id.textViewSmoking);
                final TextView tvSize = viewDialog.findViewById(R.id.textViewSize);
                final TextView tvDate = viewDialog.findViewById(R.id.textViewDate);
                final TextView tvTime = viewDialog.findViewById(R.id.textViewTime);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Confirm Your Order");

                tvName.setText("Name: " + name.getText().toString());
                tvSmoking.setText(checked);
                tvSize.setText("Size: "+ size.getText().toString());
                tvDate.setText("Date: "+ date.getText().toString());
                tvTime.setText("Time: "+ time.getText().toString());

                myBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
                myBuilder.setNeutralButton("Cancel", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name.setText("");
                phone.setText("");
                smoke.setChecked(false);
                size.setText("");
                date.setText("");
                time.setText("");
            }
        });
    }
}
