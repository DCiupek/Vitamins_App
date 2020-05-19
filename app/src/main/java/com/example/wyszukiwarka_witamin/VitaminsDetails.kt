package com.example.wyszukiwarka_witamin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class VitaminsDetails : AppCompatActivity() {

    lateinit var vitamin: Vitamins
    lateinit var idTextView: TextView
    lateinit var nadmiarTextView: TextView
    lateinit var niedoborTextView: TextView
    lateinit var wystepowanieTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vitamins_details)

        vitamin = intent.getParcelableExtra(MainActivity.INTENT_VITAMIN_KEY)

        idTextView = findViewById(R.id.id_textview)
        nadmiarTextView = findViewById(R.id.objawynadmiaru_textview)
        niedoborTextView = findViewById(R.id.objawyniedoboru_textview)
        wystepowanieTextView = findViewById(R.id.wystepowanie_textview)

        idTextView.text = vitamin.id
        nadmiarTextView.text = vitamin.objawy_nadmiaru?.joinToString(separator = "\n")
        niedoborTextView.text = vitamin.objawy_niedoboru?.joinToString(separator = "\n")
        wystepowanieTextView.text = vitamin.wystepowanie?.joinToString(separator = "\n")



    }
}


