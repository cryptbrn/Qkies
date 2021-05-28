package com.example.qkiesproject;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class kalkulator extends AppCompatActivity {
    EditText p,d,o;
    Button bhitung, reset;
    TextView thasil, rhasil, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);

        p = (EditText)findViewById(R.id.txtnumberprocess);
        d = (EditText)findViewById(R.id.txtdefect);
        o = (EditText)findViewById(R.id.txtopportunities);
        bhitung = (Button)findViewById(R.id.button);
        reset = (Button)findViewById(R.id.button2);
        thasil = (TextView)findViewById(R.id.txtdpmo);
        rhasil = (TextView)findViewById(R.id.txtsigma);
        status = (TextView)findViewById(R.id.txtstatus);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setText("");
                d.setText("");
                o.setText("");

                thasil.setText("");
                rhasil.setText("");
                status.setText("");
            }
        });


        bhitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String process = p.getText().toString();
                String defect = d.getText().toString();
                String opportunities = o.getText().toString();
                String hasil = thasil.getText().toString();

                if (process.matches("")){
                    showMessage("Please enter number of process");
                    p.requestFocus();
                }else if (defect.matches("")){
                    showMessage("Please enter number of defective process");
                    d.requestFocus();
                }else if (opportunities.matches("")){
                    showMessage("Please enter number of opportunities per defect");
                    o.requestFocus();
                }else if (hasil.matches("")){
                    showMessage("The Result is empty");
                }else{
                    double dobleprocess =Double.parseDouble(p.getText().toString());
                    double dobledefect =Double.parseDouble(d.getText().toString());
                    double dobleopportunities =Double.parseDouble(o.getText().toString());

                    thasil.setText(String.valueOf((dobledefect*1000000)/(dobleprocess*dobleopportunities)));
                }

                double doblehasil = Double.parseDouble(thasil.getText().toString());

                if(doblehasil > 308538){
                    rhasil.setText("1");
                    status.setText("sigma sangat buruk, lakukan evaluasi");
                }else if (doblehasil <= 308538 && doblehasil > 66810){
                    rhasil.setText("2");
                    status.setText("Sigma buruk, lakukan evaluasi");
                }else if (doblehasil <= 66810 && doblehasil > 6210){
                    rhasil.setText("3");
                    status.setText("Sigma cukup buruk, lalukan evaluasi");
                }else if (doblehasil <= 6210 && doblehasil > 233){
                    rhasil.setText("4");
                    status.setText("Sigma cukup baik");
                }else if (doblehasil <=233 && doblehasil > 3.4){
                    rhasil.setText("5");
                    status.setText("Sigma baik");
                }else{
                    rhasil.setText("6");
                    status.setText("Sigma sangat baik");
                }
            }
        });
    }
    void showMessage (String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
