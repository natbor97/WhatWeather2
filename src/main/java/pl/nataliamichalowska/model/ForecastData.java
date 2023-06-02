package pl.nataliamichalowska.model;

public class ForecastData {
    public long dt;
    public double temp;
    public double pressure;
    public String icon;

    public ForecastData(long dt, double temp, double pressure, String icon) {
        this.dt = dt;
        this.temp = temp;
        this.pressure = pressure;
        this.icon = icon;
    }
}
