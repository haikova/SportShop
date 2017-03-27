package by.sportshop.rent.utils;

import by.sportshop.rent.entity.RentUnit;
import by.sportshop.rent.interfaces.RentStore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olya on 26.03.2017.
 */
public class SerializationRentStore implements RentStore
{

    private String filename;

    public SerializationRentStore(String filename)
    {
        this.filename = filename;
    }

    @Override
    public List<RentUnit> loadRentUnits()
    {
        // TODO: De-serialize list of rent units from 'filename'
        List<RentUnit> rentUnits = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {
            rentUnits = (List<RentUnit>) ois.readObject();
        }
        catch (Exception ex)
        {
           //System.out.println(ex.getMessage());
        }
        return rentUnits;
    }

    @Override
    public void store(List<RentUnit> rentUnits)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oos.writeObject(rentUnits);
        }
        catch (Exception ex)
        {

            System.out.println(ex.getMessage());
        }
        // TODO: Serialize list of rent units in 'filename'
    }
}
