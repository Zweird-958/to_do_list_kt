package kotlinclasses

import java.util.*

var allMissions: MutableList<MissionClass> = mutableListOf()

class MissionClass(name: String) {

    var name = name
    var priority = allMissions.size + 1
    var done = false

    fun addToList(){
        priority = allMissions.size
        allMissions += this
        println("=========LIST==========")
        println(allMissions)
    }

    fun changePriority(increase: Boolean){

        if (allMissions.size < 2){
            return
        }

        if (increase){
            priority --
            val replacedMission = allMissions[priority]
            replacedMission.priority ++
            val indexOne = priority
            val indexTwo = replacedMission.priority
            Collections.swap(allMissions, indexOne, indexTwo)
        }
        else{
            priority ++
            val replacedMission = allMissions[priority]
            replacedMission.priority --
            val indexOne = priority
            val indexTwo = replacedMission.priority
            Collections.swap(allMissions, indexOne, indexTwo)
        }

    }

    fun checkMission(){
        done = !done
    }

}