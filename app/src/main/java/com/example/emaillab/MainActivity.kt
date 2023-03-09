package com.example.emaillab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.ItemApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    //lateinit var emails: List<Email>
    //lateinit var items: List<DisplayItem>
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager
        val summaryFragment: Fragment = SummaryFragment()
        val itemListFragment: Fragment = ItemListFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_list -> fragment = itemListFragment
                R.id.action_summary -> fragment = summaryFragment
            }
            replaceFragment(fragment)
            true
        }

        replaceFragment(ItemListFragment())

        val button = findViewById<Button>(R.id.button)
        val buttonTwo = findViewById<Button>(R.id.buttonTwo)
        button.setOnClickListener{
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        buttonTwo.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO)
            {
                (application as ItemApplication).db.itemDao().deleteAll()
            }
        }


    }

    private fun replaceFragment(listFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, listFragment)
        fragmentTransaction.commit()
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