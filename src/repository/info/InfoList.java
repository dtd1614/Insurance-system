package repository.info;

import domain.Info.Info;

import java.util.ArrayList;

public interface InfoList {
    public int add(Info info);

    public boolean delete();

    public ArrayList<Info> retrieve();

    public boolean update();
}
