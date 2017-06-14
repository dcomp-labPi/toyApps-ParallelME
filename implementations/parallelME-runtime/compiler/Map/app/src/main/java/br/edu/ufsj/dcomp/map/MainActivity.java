package br.edu.ufsj.dcomp.map;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnExecutar = (Button) findViewById(R.id.btnExecutar);
        final EditText editTextInputSize = (EditText) findViewById(R.id.editTextInputSize);
        final TextView result = (TextView) findViewById(R.id.tvResultados);
        btnExecutar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int testSize = Integer.parseInt(editTextInputSize.getText().toString());
            result.setText("Test Output:\n");
            long startTotalTime = System.currentTimeMillis();
            result.setText(result.getText().toString()+"Structure initialization: ");
            long timeStart = System.currentTimeMillis();
            ArrayTest controller = new ArrayTest();
            long timeEnd = System.currentTimeMillis();
            result.setText(result.getText().toString()+String.valueOf(timeEnd-timeStart)+"ms\n");
            result.setText(result.getText().toString()+"Operation Execution ");
            timeStart = System.currentTimeMillis();
            controller.method(testSize);
            timeEnd = System.currentTimeMillis();
            result.setText(result.getText().toString()+String.valueOf(timeEnd-timeStart)+"ms\n");
            long endTotalTime = System.currentTimeMillis();
            result.setText(result.getText().toString()+"TOTAL TIME: ");
            result.setText(result.getText().toString()+String.valueOf(endTotalTime-startTotalTime)+"ms\n");
            }
        });
    }
}
