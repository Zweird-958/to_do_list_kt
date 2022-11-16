package kotlinclasses

import android.service.autofill.Validators.or
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.DismissDirection
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import java.util.*

var allMissions: MutableList<MissionClass> = mutableListOf()
var doneMissions: MutableList<MissionClass> = mutableListOf()
var notDoneMissions: MutableList<MissionClass> = mutableListOf()
@Composable
fun initMission(){
    allMissions = remember { mutableStateListOf() }
    doneMissions = remember { mutableStateListOf() }
    notDoneMissions = remember { mutableStateListOf() }

}


class MissionClass(name: String) {

    var name = name
    var priority = notDoneMissions.size + 1
    var done = false

    fun addToList(){
        priority = notDoneMissions.size
        allMissions += this
        notDoneMissions += this
    }


    fun changePriority(increase: Boolean){

        if (notDoneMissions.size < 2){
            return
        }

        if (increase){

            if (priority == 0){
                return
            }

            priority --

            val replacedMission = notDoneMissions[priority]
            replacedMission.priority ++

            val indexOne = priority
            val indexTwo = replacedMission.priority

            Collections.swap(notDoneMissions, indexOne, indexTwo)
        }
        else{

            if (priority == notDoneMissions.size-1){
                return
            }

            priority ++

            val replacedMission = notDoneMissions[priority]
            replacedMission.priority --

            val indexOne = priority
            val indexTwo = replacedMission.priority

            Collections.swap(notDoneMissions, indexOne, indexTwo)
        }

    }


    fun updatePriority(){
        for (i in priority until notDoneMissions.size) {
            notDoneMissions[i].priority = i
        }
    }

    fun checkMission(){
        done = !done
        if (done){
            notDoneMissions.remove(this)
            doneMissions += this
            updatePriority()

        }
        else {
            doneMissions.remove(this)
            notDoneMissions += this
        }

    }

    fun deleteToList(){
        notDoneMissions.remove(this)
        updatePriority()
    }

}