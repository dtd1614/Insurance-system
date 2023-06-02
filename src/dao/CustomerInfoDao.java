package dao;

import domain.Customer;
import domain.customerInfo.CustomerInfo;
import domain.customerInfo.HomeCustomerInfo;
import domain.customerInfo.WorkplaceCustomerInfo;
import enumeration.calculationFormula.OutwallType;
import enumeration.calculationFormula.PillarType;
import enumeration.calculationFormula.RoofType;
import enumeration.calculationFormula.homeFormula.HouseType;
import enumeration.calculationFormula.homeFormula.ResidenceType;
import enumeration.calculationFormula.workplaceFormula.WorkplaceUsage;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerInfoDao extends Dao {
    public CustomerInfoDao() throws Exception {
        super();
    }
    public boolean create(CustomerInfo customerInfo) {
        String query="";
        if(customerInfo instanceof HomeCustomerInfo) {
            HomeCustomerInfo homecustomerInfo=(HomeCustomerInfo) customerInfo;
            query ="insert into customerinfo values ("+
                    customerInfo.getId()+", "+
                    "'"+customerInfo.getCustomerId()+"'"+", "+
                    customerInfo.getSquareMeter()+", "+
                    "'"+customerInfo.getPillarType()+"'"+", "+
                    "'"+customerInfo.getRoofType()+"'"+", "+
                    "'"+customerInfo.getOutwallType()+"'"+");";
            this.create(query);
            query ="insert into homecustomerinfo values ("+
                    customerInfo.getId()+", "+
                    "'"+homecustomerInfo.getHouseType()+"'"+", "+
                    "'"+homecustomerInfo.getResidenceType()+"'"+");";
            return this.create(query);
        }else {
            WorkplaceCustomerInfo workplaceCustomerInfo=(WorkplaceCustomerInfo) customerInfo;
            query ="insert into customerinfo values ("+
                    customerInfo.getId()+", "+
                    "'"+customerInfo.getCustomerId()+"'"+", "+
                    customerInfo.getSquareMeter()+", "+
                    "'"+customerInfo.getPillarType()+"'"+", "+
                    "'"+customerInfo.getRoofType()+"'"+", "+
                    "'"+customerInfo.getOutwallType()+"'"+");";
            this.create(query);
            query ="insert into workplacecustomerinfo values ("+
                    customerInfo.getId()+", "+
                    "'"+workplaceCustomerInfo.getUsage()+"'"+", "+
                    workplaceCustomerInfo.getFloor()+");";
            return this.create(query);
        }
    }
    public ArrayList<CustomerInfo> retrieve(){
        ArrayList<CustomerInfo> customerInfoList =new ArrayList<>();
        String homeInfoQuery = "select * from customerinfo, homecustomerinfo where customerinfo.id=homecustomerinfo.id;";
        String workplaceInfoQuery = "select * from customerinfo, workplacecustomerinfo where customerinfo.id=workplacecustomerinfo.id;";
        PillarType pillarType = null;
        RoofType roofType= null;
        OutwallType outwallType= null;
        HouseType houseType= null;
        ResidenceType residenceType= null;
        WorkplaceUsage workplaceUsage= null;
        resultSet = retrieve(homeInfoQuery);
        try {
            while(resultSet.next()) {
                switch(resultSet.getString(4)) {
                    case "Concrete":
                        pillarType = PillarType.Concrete;
                        break;
                    case "Brick":
                        pillarType = PillarType.Brick;
                        break;
                }
                switch(resultSet.getString(5)) {
                    case "ConcreteSlab":
                        roofType = RoofType.ConcreteSlab;
                        break;
                    case "Panel":
                        roofType = RoofType.Panel;
                        break;
                }
                switch(resultSet.getString(6)) {
                    case "Concrete":
                        outwallType = OutwallType.Concrete;
                        break;
                    case "Brick":
                        outwallType = OutwallType.Brick;
                        break;
                    case "Block":
                        outwallType = OutwallType.Block;
                        break;
                    case "Glass":
                        outwallType = OutwallType.Glass;
                        break;
                }
                switch(resultSet.getString(8)) {
                    case "Multigenerational":
                        houseType = HouseType.Multigenerational;
                        break;
                    case "multifamily":
                        houseType = HouseType.multifamily;
                        break;
                }
                switch(resultSet.getString(9)) {
                    case "SelfOwned":
                        residenceType = ResidenceType.SelfOwned;
                        break;
                    case "Rental":
                        residenceType = ResidenceType.Rental;
                        break;
                }
                HomeCustomerInfo homeCustomerInfo = new HomeCustomerInfo(resultSet.getString(2), resultSet.getInt(3), pillarType, roofType, outwallType, houseType, residenceType);
                homeCustomerInfo.setId(resultSet.getInt(1));
                customerInfoList.add(homeCustomerInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        resultSet = retrieve(workplaceInfoQuery);
        try {
            while(resultSet.next()) {
                switch(resultSet.getString(4)) {
                    case "Concrete":
                        pillarType = PillarType.Concrete;
                        break;
                    case "Brick":
                        pillarType = PillarType.Brick;
                        break;
                }
                switch(resultSet.getString(5)) {
                    case "ConcreteSlab":
                        roofType = RoofType.ConcreteSlab;
                        break;
                    case "Panel":
                        roofType = RoofType.Panel;
                        break;
                }
                switch(resultSet.getString(6)) {
                    case "Concrete":
                        outwallType = OutwallType.Concrete;
                        break;
                    case "Brick":
                        outwallType = OutwallType.Brick;
                        break;
                    case "Block":
                        outwallType = OutwallType.Block;
                        break;
                    case "Glass":
                        outwallType = OutwallType.Glass;
                        break;
                }
                switch(resultSet.getString(8)) {
                    case "Restaurant":
                        workplaceUsage = WorkplaceUsage.Restaurant;
                        break;
                    case "Store":
                        workplaceUsage = WorkplaceUsage.Store;
                        break;
                    case "LivingFacility":
                        workplaceUsage = WorkplaceUsage.LivingFacility;
                        break;
                    case "Office":
                        workplaceUsage = WorkplaceUsage.Office;
                        break;
                    case "Officetels":
                        workplaceUsage = WorkplaceUsage.Officetels;
                        break;
                    case "SportsFacility":
                        workplaceUsage = WorkplaceUsage.SportsFacility;
                        break;
                    case "Hospital":
                        workplaceUsage = WorkplaceUsage.Hospital;
                        break;
                    case "Accommodation":
                        workplaceUsage = WorkplaceUsage.Accommodation;
                        break;
                    case "Academy":
                        workplaceUsage = WorkplaceUsage.Academy;
                        break;
                    case "Church":
                        workplaceUsage = WorkplaceUsage.Church;
                        break;
                }
                WorkplaceCustomerInfo WorkplaceCustomerInfo = new WorkplaceCustomerInfo(resultSet.getString(2), resultSet.getInt(3), pillarType, roofType, outwallType, workplaceUsage, resultSet.getInt(9));
                WorkplaceCustomerInfo.setId(resultSet.getInt(1));
                customerInfoList.add(WorkplaceCustomerInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();

        }
        return customerInfoList;

    }
    public int add(CustomerInfo customerInfo) {
        ArrayList<CustomerInfo> customerInfoList = retrieve();
        if(customerInfoList.size()==0) customerInfo.setId(1);
        else {customerInfo.setId(retrieve().get(customerInfoList.size()-1).getId()+1);}
        if(create(customerInfo)) return customerInfo.getId();
        else {return 0;}
    }
    public CustomerInfo findById(int infoId) {
        for(CustomerInfo customerInfo : retrieve()) {
            if(customerInfo.getId() == infoId) return customerInfo;
        }
        return null;
    }
}
