package it.saimao.maodetector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private EditText edInput;
    private Button btnDetectFont, btnDetectLanguage;
//    private Map<Integer, Integer> maps;
    private String input, TAG = "mao_detector";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edInput = findViewById(R.id.edInput);

    }

    public void detectFont(View view) {
        Map<Integer, Integer> maps = getMaps(R.raw.detect_font);
        String result = predict(maps) >= 0 ? "Unicode" : "Zawgyi";
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();

    }

    private double predict(Map<Integer, Integer> maps) {

        double detectFontLength = 0;
        input = edInput.getText().toString();
        if (edInput.length() > 100) {
            detectFontLength = 100;
        } else {
            detectFontLength = edInput.length() - 1;
        }

        double total = 0;
        for (int i = 0; i < detectFontLength; i++) {
            int code = input.charAt(i);
//            System.out.println(code);
//            total += maps.get(code);
            if (maps.get(code) != null) {
                System.out.println(maps.get(code));
                total += maps.get(code);
            }
        }

//        String result = total / detectFontLength >= 0 ? "Unicode" : "Zawgyi";

        return total / detectFontLength;

    }

    public void detectLanguage(View view) {
        Map<Integer, Integer> maps = getMaps(R.raw.detect_language);
        String result = predict(maps) >= 0 ? "Shan" : "Myanmar";
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();

    }


    private Map<Integer, Integer> getMaps(int resource_id) {

        Map<Integer, Integer> maps = new HashMap<>();
        try {
            InputStream is = this.getResources().openRawResource(resource_id);
            ObjectInputStream ois = new ObjectInputStream(is);
            maps = (Map<Integer, Integer>) ois.readObject();
            ois.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Error loading detect_language.ser");
        }

        return maps;

    }

//    private static HashMap<Integer, Integer> forDetectFont() {
//
//        HashMap<Integer, Integer> maps = new HashMap<>();
//        // For Unicode
//        maps.put(4213, 1);
//        maps.put(4214, 1);
//        maps.put(4216, 1);
//        maps.put(4218, 1);
//        maps.put(4220, 1);
//        maps.put(4221, 1);
//        maps.put(4222, 1);
//        maps.put(4225, 1);
//        maps.put(4130, 1);
//        maps.put(4231, 1);
//        maps.put(4232, 1);
//        maps.put(4233, 1);
//        maps.put(4234, 1);
//        maps.put(4230, 1);
//        maps.put(4194, 1);
//        maps.put(4227, 1);
//        maps.put(4228, 1);
//        maps.put(4149, 1);
//        maps.put(4229, 1);
//        maps.put(4157, 1);
//        maps.put(4226, 1);
//        maps.put(4155, 1);
//        maps.put(4156, 1);
//        maps.put(4254, 1);
////        maps.put(4156, 1);
////        maps.put(4254, 1);
//        maps.put(4255, 1);
//        maps.put(4154, 1);
//        maps.put(4241, 1);
//        maps.put(4242, 1);
//        maps.put(4243, 1);
//        maps.put(4244, 1);
//        maps.put(4245, 1);
//        maps.put(4246, 1);
//        maps.put(4247, 1);
//        maps.put(4248, 1);
//        maps.put(4249, 1);
//        maps.put(4240, 1);
//        maps.put(4215, 1);
//        maps.put(43488, 1);
//        maps.put(43617, 1);
//        maps.put(4219, 1);
//        maps.put(43626, 1);
//        maps.put(43491, 1);
//        maps.put(4223, 1);
//        maps.put(43630, 1);
//        // For Zawgyi
//        maps.put(43552, -1);
//        maps.put(43542, -1);
//        maps.put(43539, -1);
//        maps.put(43538, -1);
//        maps.put(43537, -1);
//        maps.put(43527, -1);
//        maps.put(43523, -1);
//        maps.put(43522, -1);
//        maps.put(43579, -1);
//        maps.put(43588, -1);
//        maps.put(43587, -1);
//        maps.put(43586, -1);
//        maps.put(43585, -1);
//        maps.put(43584, -1);
//        maps.put(43583, -1);
//        maps.put(43582, -1);
//        maps.put(43581, -1);
//        maps.put(43580, -1);
////        maps.put(4222, -1);
////        maps.put(4221, -1);
//        maps.put(43566, -1);
////        maps.put(4156, -1);
//        maps.put(43568, -1);
//        maps.put(43569, -1);
//        maps.put(43564, -1);
//        maps.put(43555, -1);
//        maps.put(43556, -1);
//        maps.put(43567, -1);
//        maps.put(43574, -1);
//        maps.put(43573, -1);
//        maps.put(43571, -1);
//        maps.put(43570, -1);
//        maps.put(43553, -1);
//        maps.put(43551, -1);
////        maps.put(43571, -1);
////        maps.put(43570, -1);
////        maps.put(43553, -1);
////        maps.put(43551, -1);
//        maps.put(43544, -1);
//        maps.put(43541, -1);
////        maps.put(43539, -1);
//        maps.put(43529, -1);
//        maps.put(43525, -1);
//        maps.put(43521, -1);
//        maps.put(43520, -1);
//
//        return maps;
//    }
//    private static HashMap<Integer, Integer> forDetectLanguage() {
//
//        HashMap<Integer, Integer> maps = new HashMap();
//        // For Myanmar
//        maps.put(4096, 1);
//        maps.put(4097, -1);
//        maps.put(4098, -1);
//        maps.put(4101, -1);
//        maps.put(4102, -1);
//        maps.put(4103, -1);
//        maps.put(4104, -1);
//        maps.put(4106, -1);
//        maps.put(4107, -1);
//        maps.put(4108, -1);
//        maps.put(4109, -1);
//        maps.put(4110, -1);
//        maps.put(4111, -1);
//        maps.put(4114, -1);
//        maps.put(4115, -1);
//        maps.put(4116, -1);
//        maps.put(4118, -1);
////        maps.put(4118, -1);
//        maps.put(4119, -1);
//        maps.put(4120, -1);
//        maps.put(4127, -1);
//        maps.put(4128, -1);
//        maps.put(4129, -1);
//        maps.put(4146, -1);
//        maps.put(4139, -1);
//        maps.put(4140, -1);
//        maps.put(4153, -1);
//        maps.put(4105, -1);
//        maps.put(4175, -1);
//        maps.put(4159, -1);
//        maps.put(4161, -1);
//        maps.put(4162, -1);
//        maps.put(4163, -1);
//        maps.put(4164, -1);
//        maps.put(4165, -1);
//        maps.put(4166, -1);
//        maps.put(4167, -1);
//        maps.put(4168, -1);
//        maps.put(4169, -1);
//        maps.put(4160, -1);
//        maps.put(4132, -1);
//        maps.put(4173, -1);
//        maps.put(4131, -1);
//        maps.put(4174, -1);
//        maps.put(4134, -1);
//        maps.put(4133, -1);
//        maps.put(4138, -1);
//        // For Shan Unicode
//        maps.put(4213, 1);
//        maps.put(4214, 1);
//        maps.put(4216, 1);
//        maps.put(4218, 1);
//        maps.put(4220, 1);
//        maps.put(4221, 1);
//        maps.put(4222, 1);
//        maps.put(4225, 1);
//        maps.put(4130, 1);
//        maps.put(4231, 1);
//        maps.put(4232, 1);
//        maps.put(4233, 1);
//        maps.put(4234, 1);
//        maps.put(4230, 1);
//        maps.put(4194, 1);
//        maps.put(4227, 1);
//        maps.put(4228, 1);
//        maps.put(4149, 1);
//        maps.put(4229, 1);
////        maps.put(4157, 1);
//        maps.put(4226, 1);
////        maps.put(4155, 1);
////        maps.put(4156, 1);
//        maps.put(4254, 1);
////        maps.put(4156, 1);
////        maps.put(4254, 1);
//        maps.put(4255, 1);
////        maps.put(4154, 1);
//        maps.put(4241, 1);
//        maps.put(4242, 1);
//        maps.put(4243, 1);
//        maps.put(4244, 1);
//        maps.put(4245, 1);
//        maps.put(4246, 1);
//        maps.put(4247, 1);
//        maps.put(4248, 1);
//        maps.put(4249, 1);
//        maps.put(4240, 1);
//        maps.put(4215, 1);
//        maps.put(43488, 1);
//        maps.put(43617, 1);
//        maps.put(4219, 1);
//        maps.put(43626, 1);
//        maps.put(43491, 1);
//        maps.put(4223, 1);
//        maps.put(43630, 1);
//
//        return maps;
//    }


}
