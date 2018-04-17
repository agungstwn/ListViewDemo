package id.agung.android.listviewkotlindemo

import android.content.Context
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by agung on 17/04/18.
 */
class Recipes(
        val title: String,
        val description: String,
        val imageUrl: String,
        val instructionUrl: String,
        val label: String
) {
    companion object {
        fun getRecipesFromFile(filename: String, context: Context): ArrayList<Recipes>{
            val recipesList = ArrayList<Recipes>()

            try {
                //load data
                val jsonString = loadJsonFromAsset("recipes", context)
                val json = JSONObject(jsonString)
                val recipes = json.getJSONArray("recipes")

                //Get recipe object from data
                (0 until recipes.length()).mapTo(recipesList){
                    Recipes(recipes.getJSONObject(it).getString("title"),
                            recipes.getJSONObject(it).getString("description"),
                            recipes.getJSONObject(it).getString("image"),
                            recipes.getJSONObject(it).getString("url"),
                            recipes.getJSONObject(it).getString("dietLabel"))


                }
            }catch (e:JSONException){
                e.printStackTrace()
            }
            return recipesList
        }

        private fun loadJsonFromAsset(filename: String, context: Context): String? {
            var json: String? = null

            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)

                inputStream.read(buffer)
                inputStream.close()

                json = String(buffer, Charsets.UTF_8)
            }catch (ex: java.io.IOException){
                ex.printStackTrace()
                return null
            }
            return json
        }
    }

}