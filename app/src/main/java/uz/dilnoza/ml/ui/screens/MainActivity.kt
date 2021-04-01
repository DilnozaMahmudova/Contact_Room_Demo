package uz.dilnoza.ml.ui.screens


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import uz.dilnoza.ml.R
import uz.dilnoza.ml.contract.LogInContract
import uz.dilnoza.ml.model.LogInRepository
import uz.dilnoza.ml.presenter.LogInPresenter
import uz.dilnoza.ml.room.entity.UserData

class MainActivity : AppCompatActivity(), LogInContract.View {
    private lateinit var presenter: LogInPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = LogInPresenter(LogInRepository(), this)
        main_reg.setOnClickListener { presenter.clickRegistr() }
        butLogIn.setOnClickListener { presenter.clickLogIn() }
        title="Contacts"
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun moveRegistrationScreen() {
        startActivity(Intent(this, Registration::class.java))
    }

    override fun moveUserScreen(id:Long) {
startActivity(Intent(this,UserContact::class.java).putExtra("USER_ID",id))
   finish()
    }

    override fun movedminScreen() {
        startActivity(Intent(this, Admin::class.java))
    }

    override fun getLogin(): String {
        return main_login.text.toString()
    }

    override fun getPassword(): String {
        return main_password.text.toString()
    }
}
