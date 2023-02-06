package com.example.emaillab

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //lateinit var emails: List<Email>
    lateinit var items: List<Item>
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailsRV = findViewById<RecyclerView>(R.id.emailsRv)
        var items : MutableList<Item> = ArrayList()
        val adapter = ItemAdapter(items)
        emailsRV.adapter = adapter
        emailsRV.layoutManager = LinearLayoutManager(this)


        val button = findViewById<Button>(R.id.button)
        val etLink = findViewById<EditText>(R.id.etLink)
        val etMoney = findViewById<EditText>(R.id.etMoney)
        val etName = findViewById<EditText>(R.id.etName)
        button.setOnClickListener{

            val link = etLink.getText().toString()
            val money = etMoney.getText().toString()
            val name = etName.getText().toString()

            if(link == "")
            {
                Toast.makeText(
                    this, "Please input a valid URL for the item", Toast.LENGTH_SHORT).show()
            }
            else if(money == "")
            {
                Toast.makeText(
                    this, "Please input a valid price for the item", Toast.LENGTH_SHORT).show()
            }
            else if(name == "")
            {
                Toast.makeText(
                    this, "Please input a valid name for the item", Toast.LENGTH_SHORT).show()
            }
            else {
                val newItem = Item(name, money, link)
                items.add(newItem)
                emailsRV.layoutManager = LinearLayoutManager(this)


            }

        }


    }
}