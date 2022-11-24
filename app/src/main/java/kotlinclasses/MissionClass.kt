package kotlinclasses

import android.service.autofill.Validators.or
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.DismissDirection
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.todolist.MainActivity
import components.allMissions
import components.doneMissions
import components.notDoneMissions
import components.saveData
import java.util.*

var globalId = 0;

class MissionClass(name: String) {

    var id: Int = 0
    var name = name
    var priority = notDoneMissions.size + 1
    var done = false

    fun addToList(){
        this.id = globalId
        globalId++
        println("================id")
        println(id)
        priority = notDoneMissions.size
        allMissions += this
        notDoneMissions += this
        saveData()
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

        saveData()

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

        saveData()

    }

    fun deleteToList(){
        notDoneMissions.remove(this)
        updatePriority()
        saveData()
    }

}