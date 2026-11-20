
package com.example.watermeapp.data

import java.util.concurrent.TimeUnit

interface PlantRepository {

    val plants: List<Plant>

    fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        plantName: String
    )
}
