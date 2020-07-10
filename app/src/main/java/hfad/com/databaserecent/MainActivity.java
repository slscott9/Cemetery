package hfad.com.databaserecent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper myDatabaseHelper;
    ArrayList<String> names, location, graveNums, id;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        add_button = findViewById(R.id.floatingActionButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
        names = new ArrayList<>();
        location = new ArrayList<>();
        graveNums = new ArrayList<>();
        id = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, this, names, location, graveNums, id); //pass the populated arrays to the custom adapter
        recyclerView.setAdapter(customAdapter);                                                   //set the recyclers views adapter with custom adapter we made that references the cardview
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate(); //refreshes main activity to start again
        }
    }

    void storeDataInArrays(){ //get the data from the cursor object and put it in our arrays
        Cursor cursor = myDatabaseHelper.readAllData();
        if(cursor.getCount() == 0){ //if count is equal to zero that means there is no data in the arrays
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                names.add(cursor.getString(1));
                location.add(cursor.getString(2));
                graveNums.add(cursor.getString(3));
            }
        }
    }
}