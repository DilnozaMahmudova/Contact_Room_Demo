package uz.dilnoza.ml.model

import uz.dilnoza.ml.app.App
import uz.dilnoza.ml.contract.LogInContract
import uz.dilnoza.ml.room.AppDatabase
import uz.dilnoza.ml.room.entity.UserData

class LogInRepository : LogInContract.Model {
    private var userDao = AppDatabase.getDatabase(App.instance).userDao()
    override fun getUsers(): List<UserData> {
        return userDao.getAll()
    }

}