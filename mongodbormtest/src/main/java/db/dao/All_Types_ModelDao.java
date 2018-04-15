package db.dao;

import db.core.Dao;
import db.model.all_types_model.All_Types_Model;

public class All_Types_ModelDao extends Dao<All_Types_Model> {

    public All_Types_ModelDao() {
    }

    @Override
    public String getCollectionName() {
        return "all_types_model";
    }

    @Override
    public Class<All_Types_Model> getModelClass() {
        return All_Types_Model.class;
    }


}
