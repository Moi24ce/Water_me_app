
package com.example.watermeapp.data

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.watermeapp.worker.WaterReminderWorker
import java.util.concurrent.TimeUnit

class WorkManagerWaterRepository(
    context: Context
) : PlantRepository {

    private val workManager = WorkManager.getInstance(context)

    override val plants: List<Plant>
        get() = LocalPlantDataProvider.plants

    override fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        plantName: String
    ) {

        // Bước 1: Tạo dữ liệu truyền vào Worker
        val data = Data.Builder()
            .putString(
                WaterReminderWorker.nameKey,
                plantName
            )
            .build()

        // Bước 2: Tạo yêu cầu công việc
        val workRequest =
            OneTimeWorkRequestBuilder<WaterReminderWorker>()
                .setInitialDelay(duration, unit)
                .setInputData(data)
                .build()

        // Bước 3: Đưa công việc vào WorkManager
        workManager.enqueueUniqueWork(
            plantName,
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }
}
