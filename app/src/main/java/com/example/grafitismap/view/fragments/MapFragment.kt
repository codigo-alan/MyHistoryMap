package com.example.grafitismap.view.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.grafitismap.R
import com.example.grafitismap.databinding.FragmentMapBinding
import com.example.grafitismap.models.MarkerModel
import com.example.grafitismap.viewmodel.GrafitisViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


const val REQUEST_CODE_LOCATION = 100


class MapFragment : Fragment(), OnMapReadyCallback {

    lateinit var map: GoogleMap
    private lateinit var binding: FragmentMapBinding
    private val viewModel : GrafitisViewModel by activityViewModels()

    //self location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var currentCoordinates: LatLng

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMapBinding.inflate(inflater, container, false)
        //createMap()

        fusedLocationProviderClient =  LocationServices.getFusedLocationProviderClient(requireActivity())
        getLocation()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        enableLocation()
        moveToNewMarker()
        viewModel.markersEntityLiveData.observe(viewLifecycleOwner){
            it.forEach { item -> getMarker(LatLng(item.latitude.toDouble(),item.longitude.toDouble())) }
            viewModel.newMarkerTemp =
                MarkerModel(
                    name = "",
                    category = "",
                    photo = "",
                    latitude = -1.0,
                    longitude = -1.0)
        }

        map.setOnMapLongClickListener {coordinates ->
            viewModel.newMarkerTemp =
                MarkerModel(name = "",category = "", photo = "", latitude = coordinates.latitude, longitude = coordinates.longitude)
            findNavController().navigate(R.id.action_mapFragment_to_addMarkerFragment)
        }

    }

    private fun moveToNewMarker() {

        //newMarker
        if (viewModel.newMarkerTemp.name != "") {
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(viewModel.newMarkerTemp.latitude,viewModel.newMarkerTemp.longitude), 18f),
                5000, null)
        } else {
            //move to self location
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(currentCoordinates, 18f),
                5000, null)
        }


    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (isLocationPermissionGranted()) {
            fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                val location = it.result
                if (location != null) {
                    currentCoordinates = LatLng(location.latitude, location.longitude)
                    createMap()
                }
            }
        } else {
            requestLocationPermission()
        }
    }


    private fun createMap(){
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun getMarker(coordinates: LatLng){
        val myMarker = MarkerOptions().position(coordinates)
        map.addMarker(myMarker)
    }

    private fun isLocationPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun enableLocation(){
        if(!::map.isInitialized) return
        if(isLocationPermissionGranted()){
            if (context?.let {
                    ActivityCompat.checkSelfPermission(
                        it,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                } != PackageManager.PERMISSION_GRANTED && context?.let {
                    ActivityCompat.checkSelfPermission(
                        it,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                } != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            map.isMyLocationEnabled = true
        }
        else{
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(requireContext(), "Diríjete a la pantalla de permisos de la app y habilita permisos de geolocalización", Toast.LENGTH_SHORT).show()
        }
        else{
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
                if (context?.let {
                        ActivityCompat.checkSelfPermission(
                            it,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    } != PackageManager.PERMISSION_GRANTED && context?.let {
                        ActivityCompat.checkSelfPermission(
                            it,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    } != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                map.isMyLocationEnabled = true
                getLocation()
            }
            else{
                Toast.makeText(requireContext(), "Acepta los permisos de geolocalización",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(!::map.isInitialized) return
        if(!isLocationPermissionGranted()){
            if (context?.let {
                    ActivityCompat.checkSelfPermission(
                        it,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                } != PackageManager.PERMISSION_GRANTED && context?.let {
                    ActivityCompat.checkSelfPermission(
                        it,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                } != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            map.isMyLocationEnabled = false
            Toast.makeText(requireContext(), "Acepta los permisos de geolocalización",
                Toast.LENGTH_SHORT).show()
        }
    }

}