package aiactr.archit.decode;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    String ElectionDay = "2019-05-12";      //yyyy-mm-dd
    int flagBack = 1;
    String epicEntered;
    TextView pollingStationName;
    EditText textEpic;
    Spinner spinner2;
    Spinner spinner1;
//    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    MapView mapView;
    GoogleMap map;
    LatLng cc = new LatLng(28.6505,77.2303);
    EditText epicNoEditText;
    EditText mobileNoEdiText;
    Button button ;
//    EditText otp;

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
//        spinner1 = findViewById(R.id.idDropTimeSpinner);
//        spinner2 = findViewById(R.id.idPickupTimeSpinner);
        epicNoEditText= findViewById(R.id.idEpicNo);
        mobileNoEdiText = findViewById(R.id.idMobileNo);
//        otp = findViewById(R.id.idetOTP);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);




//        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

//        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());

//        List<String> picktimeList = new ArrayList<String>();
//        picktimeList.add("Select Time");
//        picktimeList.add("10:00AM - 11:00AM");
//        picktimeList.add("11:00AM - 12:00PM");
//        picktimeList.add("01:00PM - 02:00PM");
//        picktimeList.add("02:00PM - 03:00PM");
//        picktimeList.add("03:00PM - 04:00PM");
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String >(this,R.layout.simple_spinner_item,picktimeList){
//            @Override
//            public boolean isEnabled(int position){
//                if(position == 0)
//                {
//                    // Disable the first item from Spinner
//                    // First item will be use for hint
//                    return false;
//                }
//                else
//                    {
//                        return true;
//                    }
//            }
//            @Override
//            public View getDropDownView(int position, View convertView, ViewGroup parent) {
//                minimizeKeypad();
//                View view = super.getDropDownView(position, convertView, parent);
//                TextView tv = (TextView) view;
//                if(position == 0){
//                    // Set the hint text color gray
//                    tv.setTextColor(Color.GRAY);
//                }
//                else {
//                    tv.setTextColor(Color.BLACK);
//                }
//                return view;
//            }
//        };
//        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//        spinner1.setAdapter(arrayAdapter);
//        spinner2.setAdapter(arrayAdapter);
//        spinner1.setPrompt("Select Time");
//        spinner2.setPrompt("Select Time");
//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                spinner1.setSelection(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                spinner2.setSelection(position);
//
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
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
        epicEntered = textEpic.getText().toString();
        switch(epicEntered)
        {
            case "ABCD12345" :
                pollingStationName.setText("Jai Bharat School, Barsha Bula");
                break;
            case "xxxx12345" :
                pollingStationName.setText("Katra Neel, Chandni Chowk");
                break;
        }
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
        String uri = "https://www.pdffiller.com/jsfiller-mob10/?projectId=283719446&expId=4604&expBranch=1#95564fedce244799a2a49d8af2e407b8";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
    }

    ////////////////////////////////////////////////////////
    // For Fragment Facilities /////////////////////////////
    ////////////////////////////////////////////////////////
//    int otpNo;
    String mobileNo="";
    EditText mobile;
    String phoneNo;
//    String message;
/*

    protected void sendSMSMessage() {
        Log.i("here","000000");

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        mobile = findViewById(R.id.idMobileNo);
        phoneNo = mobile.getText().toString();
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

    public void onGetOtpButtonPressed(View view){
        LinearLayout linearLayoutOT = findViewById(R.id.idlayoutForOTP);
        if(!checkAllfields())
            return ;
        otpNo = generateOtpno();
        sendOTP(otpNo);
        message = "Otp for login is : "+otpNo;
        sendSMSMessage();

//        Toast.makeText(getApplicationContext(), "This OTP No will come via message for Now OTP  :"+otpNo, Toast.LENGTH_LONG).show();
        findViewById(R.id.idEpicNo).setEnabled(false);
        findViewById(R.id.idMobileNo).setEnabled(false);
//        findViewById(R.id.idbtnsumbit).setEnabled(false);
        linearLayoutOT.setVisibility(View.VISIBLE);

        minimizeKeypad();
    }

    public void sendOTP(Integer otp)
    {
        //Todo make function  to use sms gateway service to send otpNo , for  genearting OTP  using toast
    }

    public void onSubmitButtonPressed(View view){
        EditText otpEditText = findViewById(R.id.idetOTP);
        String otpNoEditText = otpEditText.getText().toString();
        int enteredOTP = Integer.parseInt(otpNoEditText);
        if(otpNo == enteredOTP )
        {
            saveInDatabase();
            Toast.makeText(getApplicationContext(), "Thanks for registering you will get a message before vehicle arrives ", Toast.LENGTH_LONG).show();
            finish();
            startActivity(getIntent());
        }
        else{
            Toast.makeText(getApplicationContext(), "Invalid otp", Toast.LENGTH_SHORT).show();
        }
    }
*/

    public void saveInDatabase(){
//        String epicNo = getEpicNo();
        String mobileNo = getMobileNo();
        /*String pickUpTime = getPickTime();
        String dropTime = getdropTime();*/
    }

    private void minimizeKeypad() {
        //code to minimize the keypad opened after entering the mobile no
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

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

/*
    public int generateOtpno(){

        Log.i("here","555555555555555555555555555555555");
        int range = 9;  // to generate a single number with this range, by default its 0..9
        int length = 4; // by default length is 4
        int randomNumber;

        SecureRandom secureRandom = new SecureRandom();
        String s = "";
        for (int i = 0; i < length; i++) {
            int number = secureRandom.nextInt(range);
            if (number == 0 && i == 0) { // to prevent the Zero to be the first number as then it will reduce the length of generated pin to three or even more if the second or third number came as zeros
                i = -1;
                continue;
            }
            s += number;
        }

        randomNumber = Integer.parseInt(s);
        Log.i("here","6666666666666666666666666666666666666");
        return randomNumber;
    }

*/
    private boolean checkAllfields(){

        String epicNo = getEpicNo();
       /* String pickupTime = getPickTime();*/
       /* String dropTime = getdropTime();*/

       //Todo check the validity of the epic no from the database server , showing error toast if nothing is entered in epci no
        if (epicNo.matches("")) {
            Toast.makeText(getApplicationContext(), "Error!!! Epic No Not entered", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!isValidMobile(mobileNo)) {
            Toast.makeText(getApplicationContext(), "Error!!! Invalid Mobile No entered", Toast.LENGTH_SHORT).show();
            return false;
        }/*else if(pickupTime.equals("Select Time")){
            Toast.makeText(getApplicationContext(), " Error!!! PickUpTime Not selected ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(dropTime.equals("Select Time")){
            Toast.makeText(getApplicationContext(), "DropTime Not selected ", Toast.LENGTH_SHORT).show();
            return false;
        }*/else if(MobileNoExitsinDatabase()){
            Toast.makeText(getApplicationContext(), "Already registered Mobile no ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean MobileNoExitsinDatabase(){
        return false;
    }

    private boolean isValidMobile(String phoneNo) {
//        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
//        Matcher m = p.matcher(phoneNo);
//        return (m.find() && m.group().equals(phoneNo));
        return true;
    }
   /* private String getdropTime() {
        Spinner mySpinnerDropTime = findViewById(R.id.idDropTimeSpinner);
        String dropTime = mySpinnerDropTime.getSelectedItem().toString();
        return dropTime;
    }

    private String getPickTime() {
        Spinner mySpinnerPickTime = findViewById(R.id.idPickupTimeSpinner);
        String pickupTime = mySpinnerPickTime.getSelectedItem().toString();
        return pickupTime;
    }*/

    private String getMobileNo() {
        mobileNoEdiText = findViewById(R.id.idEpicNo);
        String mobileNo =  mobileNoEdiText.getText().toString();
        if (mobileNo.equals(null))
        {
            mobileNo = "";
        }
        return mobileNo;
    }

    private String getEpicNo() {
        epicNoEditText = findViewById(R.id.idEpicNo);
        String epicNo =  epicNoEditText.getText().toString();
        if (epicNo.equals(null))
        {
            epicNo = "";
        }
        return  epicNo;
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
