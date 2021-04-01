package uz.dilnoza.ml.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.dilnoza.ml.room.dao.ContactDao
import uz.dilnoza.ml.room.dao.UserDao
import uz.dilnoza.ml.room.entity.ContactData
import uz.dilnoza.ml.room.entity.UserData

@Database(entities = [ContactData::class, UserData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao():ContactDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}