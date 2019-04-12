package aiactr.archit.decode;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String ElectionDay = "2019-05-12";      //yyyy-mm-dd
    int flagBack = 1;
    TextView pollingStationName;
    EditText textEpic;
    LatLng cc = new LatLng(28.6505,77.2303);
    EditText epicNoEditText;
    EditText mobileNoEdiText;
    Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        HomeFragment fragment = new HomeFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

        //checking if elections complete
        checkDay();
        textEpic = findViewById(R.id.etepicno);
        pollingStationName = findViewById(R.id.name);
        epicNoEditText= findViewById(R.id.idEpicNo);
        mobileNoEdiText = findViewById(R.id.idMobileNo);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    public void spinnerToast()
    {
        Toast.makeText(MainActivity.this, R.string.time_prompt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (flagBack==0)
        {
            flagBack=1;
            goHome();
        }
        else {
            flagBack=0;
            super.onBackPressed();
        }
    }

    public void checkDay()
    {
//        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try{
            Date theDay = format.parse(ElectionDay);

//            Log.i("date","today is "+date);

//            if (today.after(theDay)> 0) {
            if (new Date().after(theDay)){
                Log.i("Election status","Not over");
                //Election not over enabling the home fragment
                HomeFragment fragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            }else
            {
                Log.i("Election status","over");
                AfterFragment afterFragment = new AfterFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, afterFragment).commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
//            ParliamentConstituencyFragment fragment = new ParliamentConstituencyFragment();
//            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }
    }

    ////////////////////////////////////////////////////////
    // For Fragment Home   /////////////////////////////////
    ////////////////////////////////////////////////////////
    public void goHome()
    {
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
    }
    public void openCC(View view)
    {
        flagBack=0;
        ParliamentConstituencyFragment parliamentConstituencyFragment = new ParliamentConstituencyFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, parliamentConstituencyFragment).commit();
    }
    public void openPS(View view)
    {
        flagBack=0;
        PollingStationFragment pollingStationFragment = new PollingStationFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,pollingStationFragment).commit();
    }
    public void openInfo(View view)
    {
        flagBack=0;
        InfoFragment infoFragment = new InfoFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, infoFragment).commit();
    }
    public void openPWD(View view)
    {
        flagBack=0;
        FacilitiesFragment facilitiesFragment = new FacilitiesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, facilitiesFragment).commit();
    }
    public void openThirdG(View view)
    {
        flagBack=0;
        FacilitiesFragment facilitiesFragment = new FacilitiesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, facilitiesFragment).commit();
    }
    public void openKYC(View view)
    {
        flagBack=0;
        CandidateFragment candidateFragment = new CandidateFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, candidateFragment).commit();
    }
    public void openFeedbackPage(View view)
    {
        flagBack=0;
        FeedbackFragment feedbackFragment = new FeedbackFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, feedbackFragment).commit();
    }

    ////////////////////////////////////////////////////////
    // For Fragment Parliament Constituency ////////////////
    ////////////////////////////////////////////////////////
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        map=googleMap;
//        map.getUiSettings().setAllGesturesEnabled(true);
//
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(cc);
//        map.addMarker(markerOptions);
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(28.6632701,77.4291378),12));  //map position
//
////        UiSettings uiSettings = map.getUiSettings();
////        uiSettings.setAllGesturesEnabled(true);
//    }

    ////////////////////////////////////////////////////////
    // For Fragment Polling Station ////////////////////////
    ////////////////////////////////////////////////////////

    public void goButton(View view)
    {
        //TODO search the names of polling station
    }

    ////////////////////////////////////////////////////////
    // For Fragment Information ////////////////////////////
    ////////////////////////////////////////////////////////

    public void openWebsite(View view)
    {
        //Opening the eci training website
        String uri = "http://ecisveep.nic.in/voters/how-to-vote/";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
    }

    ////////////////////////////////////////////////////////
    // For Fragment Know your candidate ////////////////////
    ////////////////////////////////////////////////////////

    public void downloadPdf(View view)
    {
        //give the download link of the pdf
        String uri = "https://";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
    }

    ////////////////////////////////////////////////////////
    // For Fragment Facilities /////////////////////////////
    ////////////////////////////////////////////////////////

    EditText mobile;
    EditText epic;
    String epicEntered;
    String phoneNoEntered;

    public void submitEpic(View view)
    {
        mobile = findViewById(R.id.idMobileNo);
        epic = findViewById(R.id.idEpicNo);
        phoneNoEntered = mobile.getText().toString();
        epicEntered = epic.getText().toString();
        if (checkAllFields(epicEntered, phoneNoEntered))
        {
            saveInDatabase(epicEntered, phoneNoEntered);
            Toast.makeText(this, "You will get a SMS for confirmation", Toast.LENGTH_LONG).show();
        }
        goHome();
    }

    public void saveInDatabase(String epic, String phone){
        //TODO save in database
    }

//    private void minimizeKeypad() {
//        //code to minimize the keypad opened after entering the mobile no
//        View view = this.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }

    public void openPlayStore(View view)
    {
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=pwd.eci.com.pwdapp&hl=en_IN"));
            startActivity(intent);
        }catch (Exception ex){
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=pwd.eci.com.pwdapp&hl=en_IN"));
            startActivity(intent);
        }
    }

    private boolean checkAllFields(String epic, String phone){

       //Todo check the validity of the epic no from the database server , showing error toast if nothing is entered in epci no
        if (epic.matches("")) {
            Toast.makeText(getApplicationContext(), "Error!!! Epic No Not entered", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (phone.length()<10) {
            Toast.makeText(getApplicationContext(), "Error!!! Invalid Mobile No entered", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /////////////////////////////////////
    //// Log In /////////////////////////
    /////////////////////////////////////

    public void goToLogIn(View view)
    {
        //Open Log in Activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
