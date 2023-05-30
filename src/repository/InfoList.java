package repository;

import domain.Info.Info;

import java.util.ArrayList;

public class InfoList extends DBConnector{
    private final ArrayList<Info> infoList = new ArrayList<>();
    public int add(Info info) {
        if(infoList.size()==0)info.setId(1);
        else {info.setId(infoList.get(infoList.size()-1).getId()+1);}
        if(infoList.add(info)) return info.getId();
        else {return 0;}
    }
    public ArrayList<Info> retrieve() {
        return this.infoList;
    }
    public Info findById(int infoId) {
        for(Info info : infoList) {
            if(info.getId() == infoId) return info;
        }
        return null;
    }
}
