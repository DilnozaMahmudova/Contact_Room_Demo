package uz.dilnoza.ml.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.admin_act.*
import uz.dilnoza.ml.R
import uz.dilnoza.ml.contract.AdminContract
import uz.dilnoza.ml.model.AdminRepository
import uz.dilnoza.ml.presenter.AdminPresenter
import uz.dilnoza.ml.room.entity.UserData
import uz.dilnoza.ml.ui.adapters.UserAdapter
import uz.dilnoza.ml.ui.dialogs.UserDialog

class Admin : AppCompatActivity(), AdminContract.View {
    private lateinit var presenter: AdminContract.Presenter
    private val adapter = UserAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_act)
        title="Admin"
        presenter = AdminPresenter(AdminRepository(), this)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        adapter.setOnItemClickListener(presenter::openUser)
        adapter.setOnItemDeleteListener(presenter::deleteUser)
        adapter.setOnItemEditListener(presenter::editUser)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAdd -> presenter.openAddUser()
            R.id.menuBack -> finish()
        }
        return true
    }

    override fun loadData(data: List<UserData>) {
        adapter.submitList(data)
    }

    override fun openEditDialog(userData: UserData) {
        val dialog = UserDialog(this, "Edit")
        dialog.setUserData(userData)
        dialog.setOnClickListener(presenter::confirmEditUser)
        dialog.show()
    }

    override fun openInsertDialog() {
        val dialog = UserDialog(this, "Add")
        dialog.setOnClickListener(presenter::createUser)
        dialog.show()
    }

    override fun openContact(userID: Long) {
        startActivity(Intent(this, UserContact::class.java).apply { putExtra("USER_ID", userID) })
        finish()
    }

    override fun deleteItem(userData: UserData) {
        adapter.removeItem(userData)
    }

    override fun updateItem(userData: UserData) {
        adapter.updateItem(userData)
    }

    override fun insertItem(userData: UserData) {
        adapter.insertItem(userData)
        list.smoothScrollToPosition(adapter.itemCount - 1)
    }
}