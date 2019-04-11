package aiactr.archit.decode;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ParliamentConstituencyFragment extends Fragment implements OnMapReadyCallback{

    TextView heading;
    Drawable headingBackground;
    MapView mapView;
    GoogleMap map;
    LatLng cc = new LatLng(28.6632701,77.4291378);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_parliament_constituency, container,false);

        heading = view.findViewById(R.id.heading_cc);

        headingBackground = heading.getBackground();
        headingBackground.setAlpha(170);

        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        map.getUiSettings().setAllGesturesEnabled(true);

        MapsInitializer.initialize(this.getActivity());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(cc);
        map.addMarker(markerOptions);
        map.addMarker(new MarkerOptions().position(new LatLng(28.6500,77.2300)));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(cc,2));  //map position

//        UiSettings uiSettings = map.getUiSettings();
//        uiSettings.setAllGesturesEnabled(true);
    }
}