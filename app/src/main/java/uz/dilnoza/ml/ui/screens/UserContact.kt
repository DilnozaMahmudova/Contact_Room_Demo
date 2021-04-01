package uz.dilnoza.ml.ui.screens

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.user_act.*
import uz.dilnoza.ml.R
import uz.dilnoza.ml.contract.ContactContract
import uz.dilnoza.ml.model.ContactRepository
import uz.dilnoza.ml.presenter.ContactPresenter
import uz.dilnoza.ml.room.AppDatabase
import uz.dilnoza.ml.room.entity.ContactData
import uz.dilnoza.ml.ui.adapters.ContactAdapter
import uz.dilnoza.ml.ui.dialogs.ContactDialog

class UserContact : AppCompatActivity(), ContactContract.View {
    private val userId: Long by lazy { intent.getLongExtra("USER_ID", 0) }
    private lateinit var presenter: ContactPresenter
    private val adapter = ContactAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_act)
        presenter = ContactPresenter(ContactRepository(), this, userId)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        adapter.setOnItemEditListener(presenter::editContact)
        adapter.setOnItemDeleteListener(presenter::deleteContact)
        title="UserContact"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAdd -> presenter.openAddContact()
            R.id.menuBack -> finish()
        }
        return true
    }

    override fun loadData(data: List<ContactData>) {
        adapter.submitList(data)
    }

    override fun openEditDialog(contactData: ContactData) {
        val dialog = ContactDialog(this, "Edit")
        dialog.setContactData(contactData)
        dialog.setOnClickListener(presenter::confirmEditContact)
        dialog.show()
    }

    override fun openInsertDialog() {
        val dialog = ContactDialog(this, "Add")
        dialog.setOnClickListener(presenter::createContact)
        dialog.show()
    }

    override fun deleteItem(contactData: ContactData) {
        adapter.removeItem(contactData)
    }

    override fun updateItem(contactData: ContactData) {
        adapter.updateItem(contactData)
    }

    override fun insertItem(contactData: ContactData) {
        adapter.insertItem(contactData)
    }
}

