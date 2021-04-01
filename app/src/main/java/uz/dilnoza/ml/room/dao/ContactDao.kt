package uz.dilnoza.ml.room.dao

import androidx.room.Dao
import androidx.room.Query
import uz.dilnoza.ml.room.entity.ContactData
@Dao
interface ContactDao :BaseDao<ContactData>{
    @Query("SELECT * FROM ContactData")
    fun getAll(): List<ContactData>
    @Query("DElete From ContactData where userId=:id")
    fun deleteAllByUserId(id:Long)
    @Query("SELECT * From ContactData where userId=:userId")
    fun getContactsByUserId(userId: Long?):List<ContactData>


}