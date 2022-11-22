package com.gooner.domain.model

enum class EventDetailsFilter(val value: String) {
    NORMAL_GOAL("Normal Goal"),
    OWN_GOAL("Own Goal"),
    PENALTY("Penalty"),
    MISSED_PENALTY("Missed Penalty"),
    YELLOW_CARD("Yellow Card"),
    SECOND_YELLOW_CARD("Second Yellow card"),
    RED_CARD("Red Card"),
    SUBSTITUTION("Substitution"),
    GOAL_CANCELLED("Goal cancelled"),
    PENALTY_CONFIRMED("Penalty confirmed")
}

enum class EventTypeFilter(val value: String) {
    GOAL("Goal"),
    CARD("Card"),
    SUBSTITUTION("subst"),
    VAR("Var")
}

fun getEventDetailFilter(value: String): EventDetailsFilter?{
    val map = EventDetailsFilter.values().associateBy(EventDetailsFilter::value)
    return map[value]
}

fun getEventTypeFilter(value: String): EventTypeFilter?{
    val map = EventTypeFilter.values().associateBy(EventTypeFilter::value)
    return map[value]
}
