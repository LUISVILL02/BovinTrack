package com.seminario.bovintrack.utils

import com.google.maps.android.SphericalUtil
import com.google.android.gms.maps.model.LatLng

fun calculatePolygonAreaInHectares(points: List<LatLng>): Int {
    val areaInSquareMeters = SphericalUtil.computeArea(points)
    val areaInHectares = areaInSquareMeters / 10000.0
    return areaInHectares.toInt()
}