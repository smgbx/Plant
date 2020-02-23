package com.example.plantv2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_add_plant.setOnClickListener {
            val intent = Intent(this, CreatePlantActivity::class.java)
            startActivity(intent)
        }
    }
}