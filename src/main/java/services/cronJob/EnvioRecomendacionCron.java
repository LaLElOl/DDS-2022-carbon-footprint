package services.cronJob;

import lombok.Getter;
import lombok.Setter;

import java.util.Timer;
import java.util.TimerTask;

@Getter
@Setter
public class EnvioRecomendacionCron {

    int tiempoCron;

    public EnvioRecomendacionCron(int cantDias){
        this.tiempoCron = cantDias * 86400;
    }

    public void enviarRecomendacion(){
        Timer timer = new Timer();
        TimerTask job = new RecomendacionJob();
        timer.schedule(job,0,this.tiempoCron);
    }
}
