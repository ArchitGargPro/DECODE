package aiactr.archit.decode;

import android.view.View;
import android.widget.AdapterView;

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        MainActivity a = new MainActivity();
        a.spinner1.setSelection(position);
        a.spinner2.setSelection(position+1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        MainActivity a = new MainActivity();
        a.spinnerToast();
    }
}