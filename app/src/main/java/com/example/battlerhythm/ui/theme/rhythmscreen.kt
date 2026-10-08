package com.example.battlerhythm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.battlerhythm.cycleDay
import com.example.battlerhythm.data.RhythmEvent
import com.example.battlerhythm.formatTime
import java.time.LocalDate

// ---- Hardcoded for this milestone. These move to settings + Room later. ----

private const val CYCLE_LENGTH = 5
private val START_DATE: LocalDate = LocalDate.of(2026, 10, 5)

private val sampleEvents = listOf(
    RhythmEvent(1, dayIndex = 0, minuteOfDay = 6 * 60, title = "PT"),
    RhythmEvent(2, dayIndex = 0, minuteOfDay = 7 * 60 + 30, title = "School drop-off"),
    RhythmEvent(3, dayIndex = 0, minuteOfDay = 18 * 60, title = "Trash out"),
    RhythmEvent(4, dayIndex = 1, minuteOfDay = 6 * 60, title = "PT"),
    RhythmEvent(5, dayIndex = 1, minuteOfDay = 15 * 60 + 30, title = "School pickup"),
    RhythmEvent(6, dayIndex = 2, minuteOfDay = 7 * 60, title = "Laundry"),
    RhythmEvent(7, dayIndex = 2, minuteOfDay = 19 * 60, title = "Meal prep"),
    RhythmEvent(8, dayIndex = 3, minuteOfDay = 6 * 60, title = "PT"),
    RhythmEvent(9, dayIndex = 3, minuteOfDay = 15 * 60 + 30, title = "School pickup"),
    RhythmEvent(10, dayIndex = 4, minuteOfDay = 8 * 60, title = "Groceries"),
    RhythmEvent(11, dayIndex = 4, minuteOfDay = 10 * 60, title = "Yard work"),
)

// ---------------------------------------------------------------------------

@Composable
fun RhythmScreen(modifier: Modifier = Modifier) {
    // Which cycle day is it right now?
    val today = remember { cycleDay(START_DATE, LocalDate.now(), CYCLE_LENGTH) }

    // Which day chip is selected. Starts on today; changing it redraws the screen.
    var selectedDay by remember { mutableIntStateOf(today) }

    val events = sampleEvents
        .filter { it.dayIndex == selectedDay }
        .sortedBy { it.minuteOfDay }

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Battle Rhythm",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        // Day chips: Day 1 .. Day N
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(CYCLE_LENGTH) { day ->
                FilterChip(
                    selected = day == selectedDay,
                    onClick = { selectedDay = day },
                    label = {
                        Text(if (day == today) "Day ${day + 1} (today)" else "Day ${day + 1}")
                    }
                )
            }
        }

        // Events for the selected day
        if (events.isEmpty()) {
            Text(
                text = "Nothing scheduled",
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn {
                items(events, key = { it.id }) { event ->
                    EventRow(event)
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun EventRow(event: RhythmEvent) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = formatTime(event.minuteOfDay),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = event.title,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
 