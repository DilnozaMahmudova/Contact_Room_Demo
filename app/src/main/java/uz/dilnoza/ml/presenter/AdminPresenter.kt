package uz.dilnoza.ml.presenter

import android.os.Handler
import android.os.Looper
import uz.dilnoza.ml.contract.AdminContract
import uz.dilnoza.ml.room.entity.UserData
import java.util.concurrent.Executors

class AdminPresenter(
    private val model: AdminContract.Model,
    private val view: AdminContract.View
) : AdminContract.Presenter {
    private var executor = Executors.newSingleThreadExecutor()
    private var handle = Handler(Looper.getMainLooper())
    init {
        runOnWorkerThread {
            val ls=model.getUsers()
            runOnUIThread {
        view.loadData(ls)
            }
        }
    }

    override fun openUser(userData: UserData) {
        view.openContact(userData.id)
    }

    override fun deleteUser(userData: UserData) {
        runOnWorkerThread {
        model.deleteUser(userData)
            runOnUIThread {
        view.deleteItem(userData)
            }
        }
    }

    override fun confirmEditUser(userData: UserData) {
        runOnWorkerThread {
        model.updateUser(userData)
            runOnUIThread {
        view.updateItem(userData)
            }
        }
    }

    override fun editUser(userData: UserData) {
        view.openEditDialog(userData)
    }

    override fun openAddUser() {
        view.openInsertDialog()
    }

    override fun createUser(userData: UserData) {
        runOnWorkerThread {
        model.insertUser(userData)
            runOnUIThread {
        view.insertItem(userData)
            }
        }
    }
    private fun runOnWorkerThread(f: () -> Unit) {
        executor.execute { f() }
    }

    private fun runOnUIThread(f: () -> Unit) {
        handle.post { f() }
    }

}