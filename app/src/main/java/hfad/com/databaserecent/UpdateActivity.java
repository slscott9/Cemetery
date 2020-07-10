package hfad.com.databaserecent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    public static final String ID = "id";

    EditText etName, etLocation, etGraveNums;
    Button btnUpdate;

    String name, location, graveNums, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etName = findViewById(R.id.etNameUpdate);
        etLocation = findViewById(R.id.etLocationUpdate);
        etGraveNums = findViewById(R.id.etGraveNumUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        getAndSetData(); //call the method

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(UpdateActivity.this);
                name = etName.getText().toString().trim();
                location = etLocation.getText().toString().trim();
                graveNums = etGraveNums.getText().toString().trim();
                myDatabaseHelper.updateData(id,name, location, graveNums);
            }
        });

        //Make sure to call get and set before so we have data to update

    }

    void getAndSetData(){
        if(getIntent().hasExtra("name") &&  //if there is data in the intent then we get the data and store it in our String vars
        getIntent().hasExtra("location") &&
        getIntent().hasExtra("graveNum")){
            name = getIntent().getStringExtra("name");
            location = getIntent().getStringExtra("location");
            graveNums = getIntent().getStringExtra("graveNum");
            id = getIntent().getStringExtra("id");

            etName.setText(name);                   //set our view's text with the intent data
            etLocation.setText(location);
            etGraveNums.setText(graveNums);
        }else{
            Toast.makeText(this, "No data received", Toast.LENGTH_SHORT).show();
        }
    }
}