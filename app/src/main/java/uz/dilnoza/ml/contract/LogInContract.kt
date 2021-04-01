package uz.dilnoza.ml.contract

import uz.dilnoza.ml.room.entity.UserData

interface LogInContract{
    interface Model{
        fun getUsers():List<UserData>
    }


    interface View{
        fun showMessage(message:String)
        fun moveRegistrationScreen()
        fun moveUserScreen(id:Long)
        fun movedminScreen()
        fun getLogin():String
        fun getPassword():String

    }


    interface Presenter{
        fun clickRegistr()
        fun clickLogIn()
    }

}