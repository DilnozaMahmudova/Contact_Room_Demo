package uz.dilnoza.ml.room.dao

import androidx.room.Dao
import androidx.room.Query
import uz.dilnoza.ml.room.entity.UserData

@Dao
interface UserDao :BaseDao<UserData>{
    @Query("SELECT * FROM UserData")
    fun getAll(): List<UserData>
}