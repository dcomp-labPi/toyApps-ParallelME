package br.edu.ufsj.dcomp.reduce;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
    private ArrayUserTest mArrayUserTest;
    private ArrayTest mArrayTest;
    private TextView mText;

    public void runTests(View view) {
        //mArrayUserTest.method(mText);
        mArrayTest.method(mText);
        /*
        long result = ParallelMERuntime.getInstance().createArray(new int[2]);
        int[] arr = new int[2];
        ParallelMERuntime.getInstance().toArray(result, arr);
        */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mText = (TextView) findViewById(R.id.tvResultados);
        mArrayUserTest = new ArrayUserTest(); 
        mArrayTest = new ArrayTest();
        //mArrayTest.method(mText);

        Button executar = (Button)findViewById(R.id.btnExecutar);
        executar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayTest.method(mText);
            }
        });
    }
}
