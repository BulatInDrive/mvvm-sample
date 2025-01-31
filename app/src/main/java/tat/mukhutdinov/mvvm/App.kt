package tat.mukhutdinov.mvvm

import android.app.Application
import tat.mukhutdinov.mvvm.form.ui.FormMediator

class App : Application() {

    val formMediator by lazy { FormMediator() }
}