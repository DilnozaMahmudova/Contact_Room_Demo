package uz.dilnoza.ml.ui.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.dialog_user.view.*
import uz.dilnoza.ml.R
import uz.dilnoza.ml.room.entity.UserData
import uz.dilnoza.ml.utils.SingleBlock

class UserDialog(context: Context, actionName:String) : AlertDialog(context) {
    private val contentView = LayoutInflater.from(context).inflate(R.layout.dialog_user, null, false)
    private var listener: SingleBlock<UserData>? = null
    private var userData: UserData? = null

    init {
        setView(contentView)
        setButton(BUTTON_POSITIVE, actionName) { _, _ ->
            val data = userData ?: UserData(0,"","")
            data.login= contentView.inputLogin.text.toString()
            data.password=contentView.inputPassword.text.toString()
            listener?.invoke(data)
        }
        setButton(BUTTON_NEGATIVE, "Cancel") { _, _ -> }
    }

    fun setUserData(userData: UserData) = with(contentView) {
        this@UserDialog.userData = userData
        inputLogin.setText(userData.login)
        inputPassword.setText(userData.password)
    }

    fun setOnClickListener(block: SingleBlock<UserData>) {
        listener = block
    }
}