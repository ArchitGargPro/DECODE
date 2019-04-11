package aiactr.archit.decode;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PollingStationFragment extends Fragment {

    String EPIC="";
    String location="Chandni Chowk";
    String name="Polling Station Name";
    Boolean water=true;
    Boolean police=true;
    Boolean Med=true;
    TextView StationName;
    ImageView img;
    private static int DATA_FOUND=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_polling_station, container, false);

        Button b = view.findViewById(R.id.btnGo);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout ll= view.findViewById(R.id.linearlayoutsecond);

                EditText input = view.findViewById(R.id.etepicno);
                EPIC = input.getText().toString();

                getData();
                if (DATA_FOUND==1)
                {
                    setData();
                }

                ll.setVisibility(View.VISIBLE);
            }
        });

        Button nav = view.findViewById(R.id.navigate);
        nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate();
            }
        });

        StationName = view.findViewById(R.id.name);
        img = view.findViewById(R.id.img);
        return view;
    }

    public void getData()
    {
        //TODO get the data for the entered epic number
        DATA_FOUND=1;
    }

    public void setData()
    {
        StationName.setText(name);
        img.setImageResource(R.drawable.chowk);
    }

    public void navigate()
    {
        String uri="https://maps.google.com/maps?saddr=&daddr="+location;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
    }
}
