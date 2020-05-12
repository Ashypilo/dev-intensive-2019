package ru.skillbranch.devintensive.extentions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
var unitsTime = TimeUnits.SECOND
var valueTime = 0

fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    unitsTime = units
    valueTime = value
    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()) : String {
    if (valueTime < 0) {
        valueTime *= -1
        var resul = valueTime
        while (resul > 10) {
            resul /= 10
        }
        if (unitsTime == TimeUnits.SECOND) {
            when (valueTime) {
                1 -> return "${valueTime} секунду назад"
                2, 3, 4 -> return "${valueTime} секунды назад"
                else -> return "${valueTime} секунд назад"
            }
        }
        else if (unitsTime == TimeUnits.MINUTE) {
            when (valueTime) {
                1 -> return "${valueTime} минуту назад"
                2, 3, 4 -> return "${valueTime} минуты назад"
                else -> return "${valueTime} минут назад"
            }
        }
        else if (unitsTime == TimeUnits.HOUR) {
            when (valueTime) {
                1 -> return "${valueTime} час назад"
                2, 3, 4 -> return "${valueTime} часа назад"
                else -> return "${valueTime} часов назад"
            }
        }
        else if (unitsTime == TimeUnits.DAY) {
            if (valueTime < 365) {
                when (valueTime) {
                    1 -> return "${valueTime} день назад"
                    2, 3, 4 -> return "${valueTime} дня назад"
                    else -> return "${valueTime} дней назад"
                }
            }
            else
                return "более года назад"
        }

    }
    else if (valueTime > 0) {
        var resul = valueTime
        while (resul > 10) {
            resul /= 10
        }
        if (unitsTime == TimeUnits.SECOND) {
            when (valueTime) {
                1 -> return "через ${valueTime} секунду"
                2, 3, 4 -> return "через ${valueTime} секунды"
                else -> return "через ${valueTime} секунд"
            }
        }
        else if (unitsTime == TimeUnits.MINUTE) {
            when (valueTime) {
                1 -> return "через ${valueTime} минуту"
                2, 3, 4 -> return "через ${valueTime} минуты"
                else -> return "через ${valueTime} минут"
            }
        }
        else if (unitsTime == TimeUnits.HOUR) {
            when (valueTime) {
                1 -> return "через ${valueTime} час"
                2, 3, 4 -> return "через ${valueTime} часа"
                else -> return "через ${valueTime} часов"
            }
        }
        else if (unitsTime == TimeUnits.DAY) {
            if (valueTime < 365) {
                when (valueTime) {
                    1 -> return "через ${valueTime} день"
                    2, 3, 4 -> return "через ${valueTime} дня"
                    else -> return "через ${valueTime} дней"
                }
            }
            else
                return "более чем через год"
        }
    }
    return "сейчас"
    }

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}