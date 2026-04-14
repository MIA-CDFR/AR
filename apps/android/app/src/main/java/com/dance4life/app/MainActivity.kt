package com.dance4life.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dance4life.core.domain.model.MovementObservation
import com.dance4life.plugins.rlinference.RlCoachPolicyFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CoachScreen()
                }
            }
        }
    }
}

@Composable
private fun CoachScreen() {
    val context = LocalContext.current
    val policy = remember { RlCoachPolicyFactory.create(context.applicationContext) }

    var observation by remember {
        mutableStateOf(
            MovementObservation(
                stepsLastHour = 120,
                sedentaryMinutesToday = 180,
                energyLevel = 5,
                mobilityConfidence = 6,
            )
        )
    }

    val recommendation = remember(observation, policy) { policy.recommend(observation) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Dance4Life",
            style = MaterialTheme.typography.headlineMedium,
        )
        Text(
            text = "RL movement coach recommendation",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        Text(text = "Observation")
        Text(text = "Steps last hour: ${observation.stepsLastHour}")
        Text(text = "Sedentary today: ${observation.sedentaryMinutesToday} min")
        Text(text = "Energy level: ${observation.energyLevel}/10")
        Text(text = "Mobility confidence: ${observation.mobilityConfidence}/10")

        Spacer(modifier = Modifier.padding(top = 8.dp))

        Text(text = "Recommendation")
        Text(text = recommendation.title, style = MaterialTheme.typography.titleLarge)
        Text(text = "Action: ${recommendation.actionId}")
        Text(text = "Duration: ${recommendation.durationMinutes} min")
        Text(text = recommendation.encouragementMessage)

        Spacer(modifier = Modifier.padding(top = 8.dp))

        Button(
            onClick = {
                observation = observation.copy(
                    sedentaryMinutesToday = (observation.sedentaryMinutesToday + 30).coerceAtMost(480),
                    stepsLastHour = (observation.stepsLastHour - 30).coerceAtLeast(0),
                    energyLevel = (observation.energyLevel - 1).coerceAtLeast(1),
                )
            }
        ) {
            Text("Simulate 30 more sedentary minutes")
        }

        Button(
            onClick = {
                observation = MovementObservation(
                    stepsLastHour = 120,
                    sedentaryMinutesToday = 180,
                    energyLevel = 5,
                    mobilityConfidence = 6,
                )
            }
        ) {
            Text("Reset sample")
        }
    }
}
