package com.example.wyszukiwarka_witamin

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var vitaminsRecyclerView: RecyclerView
    var displayList = mutableListOf<Vitamins>()
    lateinit var vitaminy: List<Vitamins>

    companion object {
        const val INTENT_VITAMIN_KEY = "list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val jsonFileString = getJsonDataFromAsset(applicationContext, "Witaminy.json")
        val gson = Gson()
        val listVitaminType = object : TypeToken<List<Vitamins>>() {}.type
        vitaminy= gson.fromJson(jsonFileString, listVitaminType)
        displayList
        for (vitamina in vitaminy){
            displayList.add(vitamina)
        }


        vitaminsRecyclerView = findViewById<RecyclerView>(R.id.vitamins_recyclerview)
        vitaminsRecyclerView.layoutManager = LinearLayoutManager(this)
        vitaminsRecyclerView.adapter = VitaminsSelectionRecyclerViewAdapter(displayList,this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        if (searchItem!=null){
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if(newText!!.isNotEmpty()){
                        displayList.clear()
                        val search = newText.toLowerCase()
                        vitaminy.forEach {
                            val nadmiar= it.objawy_nadmiaru?.joinToString(" ")?.toLowerCase()
                            val niedobor = it.objawy_niedoboru?.joinToString(" ")?.toLowerCase()

                            if(nadmiar!!.contains(search) or niedobor!!.contains(search) ){
                                    displayList.add(it)
                                }
                            }
                        vitaminsRecyclerView.adapter?.notifyDataSetChanged()
                    } else{
                        displayList.clear()
                        displayList.addAll(vitaminy)
                        vitaminsRecyclerView.adapter?.notifyDataSetChanged()
                    }
                    return true
                }
            })

        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showVitaminDetail(vitamin: Vitamins) {
        val vitaminDetailIntent = Intent(this, VitaminsDetails::class.java)
        vitaminDetailIntent.putExtra(INTENT_VITAMIN_KEY, vitamin)
        startActivity(vitaminDetailIntent)
    }

    fun vitaminItemClicked(witamina: Vitamins) {
        showVitaminDetail(witamina)
    }
}


