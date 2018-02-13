package mx.edu.ittepic.tam_juego_numeros_vicentemz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button b1;
    Thread thread;
    boolean ejecutar,pausado;
    double con2;
    double con = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.button);
        ejecutar=true;
        pausado=false;

    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ejecutar=true;
            if(con>5.499 && con<5.599){
                Toast.makeText(MainActivity.this,"Ganaste!",Toast.LENGTH_SHORT).show();
                ejecutar=false;
            }else {
                if(pausado==false){
                    try {
                        thread = new Thread() {
                            public void run() {
                                //ejecuta en 2do plano
                                while (ejecutar) {
                                    runOnUiThread(new Runnable() {
                                        //Ejecutar hilo en interfaz de usuario
                                        @Override
                                        public void run() {
                                            DecimalFormat df = new DecimalFormat("#.##"); String twoDigitNum = df.format(con);
                                            b1.setText(twoDigitNum);
                                        }
                                    });
                                    try {
                                        sleep(500);
                                    } catch (InterruptedException e) {
                                    }
                                    con += 0.10;
                                    if(con>=7){con=2.00;}
                                }
                            }
                        };
                        thread.start();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Error ya se ejecuto el hilo", Toast.LENGTH_SHORT).show();
                    }
                    pausado=true;
                }

            }

        }
    });
    }
}
