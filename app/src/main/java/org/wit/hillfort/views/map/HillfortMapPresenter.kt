package org.wit.hillfort.views.map

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hillfort_list.*

import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.hillfort.R
import kotlinx.android.synthetic.main.activity_hillfort_map.*
import kotlinx.android.synthetic.main.content_hillfort_map.*
import org.wit.hillfort.helpers.readImageFromPath
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.models.HillfortModel

class HillfortMapPresenter(val view: HillfortMapView) {

    var app: MainApp

    init {
        app = view.application as MainApp
    }

    fun doPopulateMap(map: GoogleMap) {
        map.uiSettings.setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(view)
        app.hillforts.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            map.addMarker(options).tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
        }
    }

    fun doMarkerSelected(marker: Marker) {
        val tag = marker.tag as Long
        val hillfort = app.hillforts.findById(tag)
        if (hillfort != null) view.showHillfort(hillfort)
    }
}