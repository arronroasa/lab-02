package com.example.listycity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    public static List<String> cities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        cities = new ArrayList<>(Arrays.asList("Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"));

        dataList = new ArrayList<>();
        dataList.addAll(cities);

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        Button addButton = findViewById(R.id.add_city_button);
        Button deleteButton = findViewById(R.id.delete_city_button);
        ListView cityListView = (ListView)findViewById(R.id.city_list);

        // The following function is from Google, Gemini, "What if I want to make a pop up that displays when a button is pressed where I can input text?", 2026-01-15
        // Slight modifications have been made
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create EditText for user input
                final EditText input = new EditText(MainActivity.this);
                input.setHint("Enter a city");

                // Build the AlertDialog pop-up
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add City")
                        .setView(input) // Put the EditText inside the popup
                        .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Run on confirmation
                                String textFromPopup = input.getText().toString();
                                dataList.add(textFromPopup);
                                cityAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", null) // Closes the popup
                        .show();
            }


        });

        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object selectedItem = parent.getItemAtPosition(position); // Select item

                String itemValue = (String) selectedItem; // Convert to string

                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { // Remove and update list of cities when the delete button is pressed
                        dataList.remove(itemValue);
                        cityAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}