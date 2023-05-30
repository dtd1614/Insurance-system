package repository;

import domain.Policy;

import java.util.ArrayList;

public class PolicyList extends DBConnector{
    private final ArrayList<Policy> policyList = new ArrayList<>();
    public int add(Policy policy) {
        if(policyList.size()==0)policy.setId(1);
        else {policy.setId(policyList.get(policyList.size()-1).getId()+1);}
        if(policyList.add(policy)) return policy.getId();
        else {return 0;}
    }

}
