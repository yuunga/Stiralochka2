package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;




public class MainActivity extends AppCompatActivity {
    private LinearLayout buttonContainer;
    int redColor = Color.parseColor("#FA8072");
    int greenColor = Color.parseColor("#8FBC8F");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonContainer = findViewById(R.id.buttonContainer);

        final String[] daysOfWeek = {
                "Понедельник",
                "Вторник",
                "Среда",
                "Четверг",
                "Пятница",
                "Суббота",
                "Воскресенье"
        };

        for (String day : daysOfWeek) {
            addButton(day);
        }
    }

    private void addButton(final String day) {
        Button dayButton = new Button(this);
        dayButton.setText(day);
        buttonContainer.addView(dayButton);

        final String[] timePeriods = {
                "10:00 - 12:00",
                "12:00 - 14:00",
                "14:00 - 16:00",
                "16:00 - 18:00",
                "18:00 - 20:00",
                "20:00 - 22:00",
                "22:00 - 00:00"
        };


        for (String timePeriod : timePeriods) {
            Button newButton = new Button(this);
            newButton.setText(day + ": " + timePeriod);
            newButton.setBackgroundResource(R.drawable.button_border);
            newButton.setBackgroundColor(greenColor);
            newButton.setVisibility(View.GONE); // Начинаем с невидимости

            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentColor = ((ColorDrawable) v.getBackground()).getColor();
                    if (currentColor == greenColor) {
                        v.setBackgroundColor(redColor);
                    } else {
                        v.setBackgroundColor(greenColor);
                    }
                }
            });

            buttonContainer.addView(newButton);
        }

        dayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < buttonContainer.getChildCount(); i++) {
                    View child = buttonContainer.getChildAt(i);
                    if (child instanceof Button) {
                        String buttonText = ((Button) child).getText().toString();
                        if (buttonText.contains(day) && child != v) {
                            child.setVisibility(child.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                        }
                    }
                }
            }
        });
    }
}
