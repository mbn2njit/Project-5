package com.example.emaillab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.ItemApplication
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    //lateinit var emails: List<Email>
    lateinit var items: List<DisplayItem>
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailsRV = findViewById<RecyclerView>(R.id.emailsRv)
        var items : MutableList<DisplayItem> = ArrayList()
        val adapter = ItemAdapter(items)
        emailsRV.adapter = adapter
        emailsRV.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            (application as ItemApplication).db.itemDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayItem(
                        entity.name,
                        entity.price,
                        entity.url
                    )
                }.also { mappedList ->
                    items.clear()
                    items.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }
        }


        val button = findViewById<Button>(R.id.button)
        //val etLink = findViewById<EditText>(R.id.etLink)
        //val etMoney = findViewById<EditText>(R.id.etMoney)
        //val etName = findViewById<EditText>(R.id.etName)
        button.setOnClickListener{

            //val link = etLink.getText().toString()
            //val money = etMoney.getText().toString()
            //val name = etName.getText().toString()

            val link = "jeremy.com"
            val money = "20"
            val name = "Jeremy"

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
                val newItem = DisplayItem(name, money, link)
                items.add(newItem)
                emailsRV.layoutManager = LinearLayoutManager(this)


            }

            val intent = Intent(this, DetailActivity::class.java)
            //startActivityForResult(intent, 444123)
            startActivity(intent)
            //super.onActivityResult(444123, 123444, intent)

            //val passedItem = intent.getStringExtra("itemName")
            //Log.d("myTag", passedItem.toString());
            //Toast.makeText(this, passedItem.toString(), Toast.LENGTH_SHORT).show()
                // deal with the item yourself

        }


    }

    /*override fun onResume()
    {
        super.onResume()

        /*(application as ItemApplication).db.articleDao().insertAll(list.map {
            ItemEntity(
                name = name,
                price = money,
                url = link
            )
        }*/

        super.onActivityResult(444123, 123444, intent)

        val passedItem = intent.getStringExtra("itemName")
        Log.d("myTag", passedItem.toString());

    }*/

    /*fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            MY_CHILD_ACTIVITY -> {
                if (resultCode == RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    val returnValue = data.getStringExtra("some_key")
                }
            }
        }
    }*/
}