package cr.ac.una.jsoncrud.dao

import cr.ac.una.jsoncrud.entity.Item
import cr.ac.una.jsoncrud.entity.Items
import retrofit2.http.*

interface ItemDAO {

        @GET("items")
        suspend fun getItems(): List<Item>

        @GET("items/{uuid}")
        suspend fun getItem(@Path("uuid") uuid: String): Item

        @POST("items")
        suspend fun createItem( @Body items: List<Item>): Items

        @PUT("items/{uuid}")
        suspend fun updateItem(@Path("uuid") uuid: String, @Body item: Item): Item

        @DELETE("items/{uuid}")
        suspend fun deleteItem(@Path("uuid") uuid: String)
}
