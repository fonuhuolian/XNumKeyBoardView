package org.fonuhuolian.numkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.fonuhuolian.xnumkeyboard.NumKeyboardListener;
import org.fonuhuolian.xnumkeyboard.XNumKeyboardView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv);
        XNumKeyboardView kbd = (XNumKeyboardView) findViewById(R.id.xv);

        kbd.setNumKeyboardListener(new NumKeyboardListener() {
            @Override
            public void onInput(int index, int textChanged, String beforeTextChanged, String afterTextChanged) {
                Log.e("index", index + "");
                Log.e("textChanged", textChanged + "-");
                Log.e("beforeTextChanged", beforeTextChanged + "-");
                Log.e("afterTextChanged", afterTextChanged + "-");

                textView.setText(afterTextChanged);
            }

            @Override
            public void onDelete(int removeIndex, String removeText, String beforeDeleteText, String afterDeleteText) {
                Log.e("removeIndex", removeIndex + "");
                Log.e("removeText", removeText + "-");
                Log.e("beforeDeleteText", beforeDeleteText + "-");
                Log.e("afterDeleteText", afterDeleteText + "-");

                textView.setText(afterDeleteText);
            }


            @Override
            public void onComplete(String content) {

                textView.setText(content);
                Log.e("onComplete", content);
            }

            @Override
            public void onReset(String textChanged, String beforeResetText) {
                textView.setText(textChanged);

                Log.e("textChanged", textChanged + "-");
                Log.e("beforeResetText", beforeResetText + "-");
            }

        });
    }
}
