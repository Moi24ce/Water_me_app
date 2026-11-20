package com.example.watermeapp.ui



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.watermeapp.data.Plant
import java.util.concurrent.TimeUnit

data class ReminderOption(
    val title: String,
    val duration: Long,
    val unit: TimeUnit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantDetailScreen(
    plant: Plant,
    onBack: () -> Unit,
    onSchedule: (Long, TimeUnit) -> Unit
) {

    val options = listOf(
        ReminderOption("15 seconds", 15, TimeUnit.SECONDS),
        ReminderOption("1 hour", 1, TimeUnit.HOURS),
        ReminderOption("12 hours", 12, TimeUnit.HOURS),
        ReminderOption("24 hours", 24, TimeUnit.HOURS)
    )

    var selectedOption by remember {
        mutableStateOf(options[0])
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Plant Details")
                },
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Text("Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =
                        MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = plant.emoji,
                    style = MaterialTheme.typography.displayLarge
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = plant.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = plant.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Recommended: ${plant.wateringFrequency}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Set watering reminder",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            options.forEach { option ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioButton(
                        selected = selectedOption == option,
                        onClick = {
                            selectedOption = option
                        }
                    )

                    Text(
                        text = option.title,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    onSchedule(
                        selectedOption.duration,
                        selectedOption.unit
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Schedule Reminder",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
