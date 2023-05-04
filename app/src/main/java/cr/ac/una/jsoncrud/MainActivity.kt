package cr.ac.una.jsoncrud

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import cr.ac.una.jsoncrud.dao.ItemDAO
import cr.ac.una.jsoncrud.entity.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor("U1xIqs39A09cmfpUSpP_Mis54d1ElzUfQx_oyB7wuCccfEkDBQ"))
            .addInterceptor(interceptor)
            .build()

        val gson = GsonBuilder().setPrettyPrinting().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://crudapi.co.uk/api/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ItemDAO::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val item = Item(_uuid = null, name = "Nuevo elemento", description = "Descripción del nuevo elemento")
            var items : ArrayList<Item>
            items= ArrayList()
            items.add(item)
            var salida= gson.toJson(item)
            print(salida)


            val createdItem = apiService.createItem( items)

            withContext(Dispatchers.Main) {
                // Procesar la respuesta del API
            }
        }
    }
}