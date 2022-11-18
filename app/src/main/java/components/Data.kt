package components

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinclasses.*
import pages.Missions

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

@Composable
fun getData() {

    initMission()
    val sharedPref = mainActivity?.getPreferences(Context.MODE_PRIVATE) ?: return

    if (!sharedPref.contains("DoneMissions")){
        saveData()
        return
    }

    val gson = Gson()

    val json = sharedPref.getString("Missions","")
    val jsonNot = sharedPref.getString("NotDoneMissions","")
    val jsonDone = sharedPref.getString("DoneMissions","")

    val allList = gson.fromJson(json, Array<MissionClass>::class.java).toMutableList()
    for (item in allList){
        allMissions += item
    }

    val notList = gson.fromJson(jsonNot, Array<MissionClass>::class.java).toMutableList()
    for (item in notList){
        notDoneMissions += item
    }

    val doneList = gson.fromJson(jsonDone, Array<MissionClass>::class.java).toMutableList()
    for (item in notList){
        doneMissions += item
    }

}
