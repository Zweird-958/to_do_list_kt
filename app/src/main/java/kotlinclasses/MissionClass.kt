package kotlinclasses

import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
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
        println(notDoneMissions)
    }

    fun changePriority(increase: Boolean){

        if (notDoneMissions.size < 2){
            return
        }

        if (increase){
            priority --

            val replacedMission = notDoneMissions[priority]
            replacedMission.priority ++

            val indexOne = priority
            val indexTwo = replacedMission.priority

            Collections.swap(notDoneMissions, indexOne, indexTwo)
        }
        else{
            priority ++

            val replacedMission = notDoneMissions[priority]
            replacedMission.priority --

            val indexOne = priority
            val indexTwo = replacedMission.priority

            Collections.swap(notDoneMissions, indexOne, indexTwo)
        }

    }

    @Composable
    fun checkBoxElement(){
        val checkedState = remember { mutableStateOf(false) }
        Checkbox(checked = checkedState.value,
            onCheckedChange = {
                this.checkMission()
                checkedState.value = it
            },
            colors = CheckboxDefaults.colors(MaterialTheme.colors.primary))
    }

    fun checkMission(){
        done = !done
        /*if (done){
            notDoneMissions.removeAt(priority)
            doneMissions += this
        }
        else {
            doneMissions.remove(this)
            notDoneMissions += this
        }*/

    }

}