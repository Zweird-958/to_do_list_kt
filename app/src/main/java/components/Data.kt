package components

import android.app.Activity
import android.content.Context
import androidx.activity.ComponentActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinclasses.MissionClass
import kotlinclasses.allMissions
import kotlinclasses.doneMissions
import kotlinclasses.notDoneMissions

var mainActivity: Activity? = null

fun getActivity(activity: Activity){
    mainActivity = activity
}

//saving data
fun saveData() {
    val sharedPref = mainActivity?.getPreferences(ComponentActivity.MODE_PRIVATE) ?: return

    val gson = Gson()
    val json = gson.toJson(allMissions)
    val jsonNot = gson.toJson(notDoneMissions)
    val jsonDone = gson.toJson(doneMissions)


    with(sharedPref.edit()) {
        putString("Missions", json)
        putString("NotDoneMissions", jsonNot)
        putString("DoneMissions", jsonDone)
        commit()
    }

}

fun getData() {

    println("====DATA")

    val sharedPref = mainActivity?.getPreferences(Context.MODE_PRIVATE) ?: return
    val gson = Gson()

    val json = sharedPref.getString("Missions","")
    val jsonNot = sharedPref.getString("NotDoneMissions","")
    val jsonDone = sharedPref.getString("DoneMissions","")

    allMissions = gson.fromJson(json, Array<MissionClass>::class.java).toMutableList()
    notDoneMissions = gson.fromJson(jsonNot, Array<MissionClass>::class.java).toMutableList()
    doneMissions = gson.fromJson(jsonDone, Array<MissionClass>::class.java).toMutableList()

}