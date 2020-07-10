package hfad.com.databaserecent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText etName, etLocation, etGraveNum;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //get references to our views
        etName = findViewById(R.id.etName);
        etLocation = findViewById(R.id.etLocation);
        etGraveNum = findViewById(R.id.etGraveNum);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() { //Create the onclick listener for our add button.

            //onClick of add button we get the text that was entered and call our database helper object's addCemetery function with adds the data in the appropriate columns in the table
            @Override
            public void onClick(View view) {
                MyDatabaseHelper dbHelper = new MyDatabaseHelper(AddActivity.this);
                dbHelper.addCemetery(etName.getText().toString().trim(), etLocation.getText().toString().trim(), Integer.valueOf(etGraveNum.getText().toString().trim()));
            }
        });


    }
}