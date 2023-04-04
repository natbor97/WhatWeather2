package pl.nataliamichalowska.model;

public class ForecastData {
    public long dt;
    public String main;
    public double temp;
    public double pressure;
    public String icon;

    public ForecastData(long dt, String main, double temp, double pressure, String icon) {
        this.dt = dt;
        this.main = main;
        this.temp = temp;
        this.pressure = pressure;
        this.icon = icon;
    }
}
