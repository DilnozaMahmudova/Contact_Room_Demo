package uz.dilnoza.ml.model

import uz.dilnoza.ml.app.App
import uz.dilnoza.ml.contract.AdminContract
import uz.dilnoza.ml.room.AppDatabase
import uz.dilnoza.ml.room.entity.UserData

class AdminRepository : AdminContract.Model {
    private val database=AppDatabase.getDatabase(App.instance)
    private var userDao = database.userDao()
    private var contactDao =database.contactDao()

    override fun getUsers(): List<UserData> {
        return userDao.getAll()
    }

    override fun updateUser(userData: UserData) {
      userDao.update(userData)
    }

    override fun deleteUser(userData: UserData) {
      userDao.delete(userData)
        contactDao.deleteAllByUserId(userData.id)
    }

    override fun insertUser(userData: UserData) {
        val id=userDao.insert(userData)
        userData.id=id
    }
}