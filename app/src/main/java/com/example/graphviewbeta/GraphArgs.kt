package com.example.graphviewbeta

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GraphArgs(var arguments: Map<String, Double>):Parcelable {
    var alpha = arguments.get("alpha")
    var beta = arguments.get("beta")
    var gamma = arguments.get("gamma")
    var delta = arguments.get("delta")
}

class LotkiVolt(var alpha: Double,var beta: Double,var gamma: Double,var delta: Double)
{
    fun fxl(x: Double, y: Double): Double {
        val a = x*(alpha-beta*y)
        return a
    }

    fun fyl(x: Double, y: Double): Double {
        val a = -y*(gamma-delta*x)
        return a
    }

    fun runge_Kutt(
        t0: Double,
        x0: Double,
        y0: Double,
        h: Double,
        b: Double
    ): List<MutableList<Double>> {
        var t = t0
        var x = x0
        var y = y0
        val arrayX= mutableListOf<Double>()
        val arrayY = mutableListOf<Double>()
        val arrayT = mutableListOf<Double>()
        while (t < b) {
            val k1 = fxl(x, y)
            val k2 = fxl(x+(0.5*k1),y+(0.5*h))
            val k3 = fxl(x+(0.5*k1),y+(0.5*h))
            val k4 = fxl(x+k3,y+h)

            val q1 = fxl(x, y)
            val q2 = fyl(x+(0.5*q1),y+(0.5*h))
            val q3 = fyl(x+(0.5*q1),y+(0.5*h))
            val q4 = fyl(x+q3,y+h)

            x += h*(k1+2*k2+2*k3+k4)/6
            y += h*(q1+2*q2+2*q3+q4)/6

            arrayX.add(x)
            arrayY.add(y)
            arrayT.add(t)
            t += h
        }
        return listOf(arrayX, arrayY, arrayT)
    }
}




