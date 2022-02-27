package com.example.graphviewbeta

import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries


class GraphFragment() : Fragment() {

    companion object {
        fun getNewInstance(args: Bundle?): GraphFragment {
            val graphFragment = GraphFragment()
            graphFragment.arguments = args
            return graphFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fun graphView() {
            val graph = view.findViewById<GraphView>(R.id.graph)
            val arguments = arguments?.getParcelable<Parcelable>("arguments") as GraphArgs?
            val alpha = arguments?.alpha ?: 0.0
            val beta = arguments?.beta ?: 0.0
            val gamma = arguments?.gamma ?: 0.0
            val delta = arguments?.delta ?: 0.0
            val graphArgs = LotkiVolt(alpha, beta, gamma, delta)
            val rung = graphArgs.runge_Kutt(0.0, 10.0, 5.0, 0.001, 100.0)
            val xarg = rung[0]
            val yarg = rung[1]
            val targ = rung[2]
            var seriesy: LineGraphSeries<DataPoint> = LineGraphSeries()
            var seriesx: LineGraphSeries<DataPoint> = LineGraphSeries()
            val pointy = arrayOfNulls<DataPoint>(targ.size)
            val pointx = arrayOfNulls<DataPoint>(targ.size)

            for (i in targ.indices) {
                pointx[i] = DataPoint(targ[i], xarg[i])
                pointy[i] = DataPoint(targ[i], yarg[i])
            }
            seriesy = LineGraphSeries(pointy)
            seriesx= LineGraphSeries(pointx)
            seriesx.color = Color.GREEN;
            graph.addSeries(seriesx)
            graph.addSeries(seriesy)
        }
        graphView()
        super.onViewCreated(view, savedInstanceState)
    }
}
