package Persistance.ReadFile;

import java.util.ArrayList;


public interface IReadDatabase<T>
{
    public ArrayList<T> ReadDatabase(String filePath);
}
