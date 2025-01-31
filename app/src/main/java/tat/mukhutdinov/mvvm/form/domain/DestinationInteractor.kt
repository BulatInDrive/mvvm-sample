package tat.mukhutdinov.mvvm.form.domain

import tat.mukhutdinov.mvvm.form.domain.model.Points
import kotlin.math.absoluteValue

class DestinationInteractor {

    suspend fun calculateTimeToDestination(points: Points): Int {
        // Сложная логика вычислений
        return (points.b.hashCode() - points.a.hashCode()).absoluteValue % 60
    }
}