package id.agung.android.listviewkotlindemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {


    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>(R.id.m_list_view)
        var recipesList = Recipes.getRecipesFromFile("recipes", this)
        var listItem = arrayOfNulls<String>(recipesList.size)

        for (i in 0 until recipesList.size) {
            val recipes = recipesList[i]
            listItem[i] = recipes.title
        }
         val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, listItem)
        listView.adapter = adapter
    }
}
