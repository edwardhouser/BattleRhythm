package com.example.battlerhythm

import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * Which day of the cycle does [today] fall on?
 * Returns 0 until [length] (so Day 1 is 0).
 * floorMod keeps the answer positive even if [start] is in the future.
 */
fun cycleDay(start: LocalDate, today: LocalDate, length: Int): Int =
    Math.floorMod(ChronoUnit.DAYS.between(start, today), length.toLong()).toInt()

/** 390 -> "0630". Events store time as minutes since midnight. */
fun formatTime(minuteOfDay: Int): String =
    "%02d%02d".format(minuteOfDay / 60, minuteOfDay % 60)