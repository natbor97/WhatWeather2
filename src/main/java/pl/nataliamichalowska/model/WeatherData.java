package pl.nataliamichalowska.model;

public class WeatherData {
    public double temp;
    public double pressure;
    public String icon;

    public WeatherData(double temp, double pressure, String icon) {
        this.temp = temp;
        this.pressure = pressure;
        this.icon = icon;
    }
}
