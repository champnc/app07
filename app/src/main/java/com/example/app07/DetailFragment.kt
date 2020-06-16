package com.example.app07

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.esri.arcgisruntime.geometry.Point
import com.esri.arcgisruntime.geometry.SpatialReferences
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.Basemap
import com.esri.arcgisruntime.mapping.view.Graphic
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay
import com.esri.arcgisruntime.mapping.view.MapView
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol
import com.ramotion.cardslider.CardSliderLayoutManager
import kotlinx.android.synthetic.main.fragment_catalog.recycleView
import kotlinx.android.synthetic.main.fragment_catalog.swipeRefreshLayout

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private lateinit var DatailAdapter: DatailAdapter

    private lateinit var mMapView: MapView

    private lateinit var myLayer: GraphicsOverlay

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMapView = view.findViewById<MapView>(R.id.mapView);

        val model: DetailViewModel by viewModels()
        model.savedDetail.observe(viewLifecycleOwner, Observer {
            // Update UI Only
            Log.d("MY_TAG", it.toString())
            for (category in it) {
                swipeRefreshLayout.isRefreshing = false

                DatailAdapter = DatailAdapter(it)
                recycleView.adapter = DatailAdapter

                recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        // stop scrolling
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            var layoutManger = recycleView.layoutManager as CardSliderLayoutManager
                            var currentShowCardPosition = layoutManger.activeCardPosition
                            var showingItem = it[currentShowCardPosition]
                            Log.d("MY_TAG",showingItem.Title)
                            Log.d("MY_TAG",showingItem.ImageURL)
                            //Log.d("MY_TAG",currentShowCardPosition.toString())
                            setupMap();

                        }
                    }
                })
            }
        })
        model.fetchNewDetail()

        swipeRefreshLayout.setOnRefreshListener {
            model.fetchNewDetail()
        }
    }

    private fun setupMap() {
        ArcGISRuntimeEnvironment.setLicense(resources.getString(R.string.arcgis_license_key));
        val basemapType = Basemap.Type.STREETS_VECTOR
        val latitude = 13.7458521
        val longitude = 100.5655998
        val levelOfDetail = 6
        val map = ArcGISMap(basemapType, latitude, longitude, levelOfDetail)
        mMapView.isAttributionTextVisible = false
        mMapView.map = map
        myLayer = GraphicsOverlay()
        mMapView.graphicsOverlays.add(myLayer)

//        val attribute = mutableMapOf<String, Any>()
//        attribute["Title"] = "Text 1"
//        attribute["Type"] = 1
        mapZoomAndShowPoint(13.7458521, 100.5655998)
    }

    override fun onPause() {
        mMapView.pause();
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        mMapView.resume();
    }

    override fun onDestroy() {
        mMapView.dispose();
        super.onDestroy()
    }

    fun mapZoomAndShowPoint(lat: Double, long: Double) {
        // clear old point
        myLayer.graphics.clear()

        // create new point
        val newPoint = Point(long, lat, SpatialReferences.getWgs84())
        val redCircleSymbol = SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.BLUE, 10F)
        val newGraphic = Graphic(newPoint, redCircleSymbol)

        // add new graphic
        myLayer.graphics.add(newGraphic)

        // zoom and center to that point
        mMapView.setViewpointCenterAsync(newPoint,2000.0)
    }
}
