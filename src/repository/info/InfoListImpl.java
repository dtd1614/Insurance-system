package repository.info;

import domain.Info.Info;

import java.util.ArrayList;

public class InfoListImpl implements InfoList{
    private final ArrayList<Info> infoList = new ArrayList<>();

    @Override
    public int add(Info info) {
        if(infoList.size()==0)info.setId(1);
        else {info.setId(infoList.get(infoList.size()-1).getId()+1);}
        if(infoList.add(info)) return info.getId();
        else {return 0;}
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public ArrayList<Info> retrieve() {
        return this.infoList;
    }

    @Override
    public boolean update() {
        return false;
    }
}
