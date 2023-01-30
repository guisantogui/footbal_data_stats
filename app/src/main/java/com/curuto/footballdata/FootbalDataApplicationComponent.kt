package com.curuto.footballdata

import com.curuto.footballdata.view.main_activity.MainActivity
import dagger.Component

@Component
interface FootbalDataApplicationComponent {

    fun inject(activity: MainActivity)
}
