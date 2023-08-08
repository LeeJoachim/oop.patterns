package observer;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(double temp, double humidity, double pressure);
}

interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

class WeatherData implements Subject{
    private List<Observer> observers = new ArrayList<Observer>();
    private double temperature;
    private double humidity;
    private double pressure;

    public WeatherData(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }
    public void setMeasurements(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
    public void registerObserver(Observer o) {
        observers.add(o);
    }
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature, humidity, pressure);
        }
    }    
}

class CurrentConditionsDisplay implements Observer {
    private double temperature;
    private double humidity;
    private double pressure;

    public void update(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity and " + pressure + " pressure");
    }
}


public class TestDriver {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData(80, 65, 30.4);
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        CurrentConditionsDisplay currentDisplay2 = new CurrentConditionsDisplay();
        CurrentConditionsDisplay currentDisplay3 = new CurrentConditionsDisplay();
        weatherData.registerObserver(currentDisplay);
        weatherData.registerObserver(currentDisplay2);
        weatherData.registerObserver(currentDisplay3);
        weatherData.setMeasurements(82, 70, 29.2);
        weatherData.setMeasurements(78, 90, 29.2);
    }
    
}
