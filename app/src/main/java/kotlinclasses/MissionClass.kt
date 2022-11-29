package kotlinclasses

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.gson.Gson
import components.allMissions
import components.doneMissions
import components.notDoneMissions
import components.saveData
import java.util.*

class MissionClass(name: String, toDo: SnapshotStateList<String>, links: SnapshotStateList<String>) {

    var name = name
    var priority = notDoneMissions.size + 1
    var done = false
    var toDo = toDo
    var links = links

    fun addToList(){
        priority = notDoneMissions.size
        allMissions += this
        notDoneMissions += this
        saveData()
    }

    fun update(name: String, toDo: SnapshotStateList<String>, links: SnapshotStateList<String>){
        this.name = name
        this.toDo = toDo
        this.links = links
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
        val isAllList: Boolean = this.isOnList(allMissions)
        val isDoneList: Boolean = this.isOnList(doneMissions)
        val isNotList: Boolean = this.isOnList(notDoneMissions)
        if (isAllList){
            allMissions.remove(this)
        }
        else if (isDoneList){
            doneMissions.remove(this)
        }
        else if (isNotList){
            notDoneMissions.remove(this)
        }
        updatePriority()
        saveData()
    }

    fun isOnList(list: SnapshotStateList<MissionClass>): Boolean {
        return list.contains(this)
    }


}
fun toJson(mission: MissionClass): String? {
    val gson = Gson()
    return gson.toJson(mission)
}

fun toClass(json: String?): MissionClass? {
    val gson = Gson()
    return gson.fromJson(json, MissionClass::class.java)
}
