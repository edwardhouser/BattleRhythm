
package com.example.battlerhythm.data

/**
 * One scheduled item in the rhythm.
 */
data class RhythmEvent(
    val id: Long = 0,
    val dayIndex: Int,      // 0 until cycle length
    val minuteOfDay: Int,   // 0..1439
    val title: String
)
 