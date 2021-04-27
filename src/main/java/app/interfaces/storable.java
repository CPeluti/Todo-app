package app.interfaces;

import app.models.Tasks;

public interface storable {
    public Tasks create();
    //public static void remainingTime(String deadline);
    public void delete();
    public void update();

}
