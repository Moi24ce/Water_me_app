package com.example.watermeapp.ui



import androidx.compose.runtime.*
import com.example.watermeapp.data.Plant
import com.example.watermeapp.data.PlantRepository

@Composable
fun WaterMeApp(
    repository: PlantRepository
) {
    var selectedPlant by remember {
        mutableStateOf<Plant?>(null)
    }

    if (selectedPlant == null) {

        PlantListScreen(
            plants = repository.plants,
            onPlantClick = { plant ->
                selectedPlant = plant
            }
        )

    } else {

        PlantDetailScreen(
            plant = selectedPlant!!,
            onBack = {
                selectedPlant = null
            },
            onSchedule = { duration, unit ->
                repository.scheduleReminder(
                    duration = duration,
                    unit = unit,
                    plantName = selectedPlant!!.name
                )

                selectedPlant = null
            }
        )
    }
}
