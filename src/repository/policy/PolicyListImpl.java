package repository.policy;

import domain.Policy;

import java.util.ArrayList;

public class PolicyListImpl implements PolicyList{
    private final ArrayList<Policy> policyList = new ArrayList<>();
    @Override
    public int add(Policy policy) {
        if(policyList.size()==0)policy.setId(1);
        else {policy.setId(policyList.get(policyList.size()-1).getId()+1);}
        if(policyList.add(policy)) return policy.getId();
        else {return 0;}
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public ArrayList<Policy> retrieve() {
        return null;
    }

    @Override
    public boolean update() {
        return false;
    }
}
