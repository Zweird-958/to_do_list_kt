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

fun getActivity(activity: Activity) {
    mainActivity = activity
}


var allMissions: SnapshotStateList<MissionClass> = mutableStateListOf()
var notDoneMissions: SnapshotStateList<MissionClass> = mutableStateListOf()
var doneMissions: SnapshotStateList<MissionClass> = mutableStateListOf()

//saving data
fun saveData() {
    val sharedPref = mainActivity?.getPreferences(ComponentActivity.MODE_PRIVATE) ?: return

    val gson = Gson()
    val json = gson.toJson(allMissions)


    with(sharedPref.edit()) {
        putString("Missions", json)
        commit()
    }

}

@Composable
fun getData() {

    val sharedPref = mainActivity?.getPreferences(Context.MODE_PRIVATE) ?: return

    if (!sharedPref.contains("DoneMissions")) {
        saveData()
        return
    }

    val gson = Gson()

    val json = sharedPref.getString("Missions", "")

    allMissions = remember { mutableStateListOf() }
    notDoneMissions = remember { mutableStateListOf() }
    doneMissions = remember { mutableStateListOf() }

    val allList = gson.fromJson(json, Array<MissionClass>::class.java).toMutableList()
    for (item in allList) {
        allMissions += item
        if (!item.done){
            notDoneMissions += item
        }
        else{
            doneMissions += item
        }
    }


}





