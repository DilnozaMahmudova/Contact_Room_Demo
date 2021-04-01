package uz.dilnoza.ml.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registration.*
import uz.dilnoza.ml.R
import uz.dilnoza.ml.contract.RegistrationContract
import uz.dilnoza.ml.model.RegistrationRepository
import uz.dilnoza.ml.presenter.RegPresenter
import uz.dilnoza.ml.room.entity.UserData
import java.util.jar.Manifest

class Registration : AppCompatActivity(), RegistrationContract.View {
    private lateinit var presenter: RegPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        presenter = RegPresenter(RegistrationRepository(), this)
        butReg.setOnClickListener { presenter.clickReg() }
        title="Registration"
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun moveLogIn() {
startActivity(Intent(this,MainActivity::class.java))
      finish()
    }

    override fun getLogin(): String {
        return username.text.toString()
    }

    override fun getPassword(): String {
        return password.text.toString()
    }

    override fun getConfirmPassword(): String {
        return repit.text.toString()
    }
}
