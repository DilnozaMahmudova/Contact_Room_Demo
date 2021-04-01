package uz.dilnoza.ml.model

import uz.dilnoza.ml.app.App
import uz.dilnoza.ml.contract.RegistrationContract
import uz.dilnoza.ml.room.AppDatabase
import uz.dilnoza.ml.room.entity.UserData

class RegistrationRepository :RegistrationContract.Model{
    private var userDao = AppDatabase.getDatabase(App.instance).userDao()
    override fun getUser(): List<UserData> {
        return userDao.getAll()
    }

    override fun insertUser(userData: UserData) {
        userDao.insert(userData)
    }
}