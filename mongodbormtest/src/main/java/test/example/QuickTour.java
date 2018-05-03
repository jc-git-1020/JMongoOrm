package test.example;

import db.core.FilterBuilder;
import db.dao.All_Types_ModelDao;
import db.model.all_types_model.All_Types_Model;

public class QuickTour {
    public static void main(String[] args) {

        All_Types_ModelDao dao = new All_Types_ModelDao();

//        All_Types_Model model = new All_Types_Model();
//        model.setStringField("tttt");
//        dao.insertOne(model);

        FilterBuilder fb = new FilterBuilder();
        fb.eqId("5aeaa9b268f5da20203db266");
        All_Types_Model model = dao.filter.find(fb).first();

        System.out.println(model.toDocument().toJson());
        System.out.println("over");

    }
}
