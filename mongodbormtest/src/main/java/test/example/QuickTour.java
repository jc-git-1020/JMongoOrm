package test.example;

import db.core.FilterBuilder;
import db.dao.All_Types_ModelDao;
import db.model.all_types_model.All_Types_Model;

public class QuickTour {
    public static void main(String[] args) {

        All_Types_ModelDao dao = new All_Types_ModelDao();
        FilterBuilder fb = new FilterBuilder();
        fb.eqId("5ad1db3aaf82cb5944733c1b");
        All_Types_Model model = dao.filter.find(fb).first();

        System.out.println(model.toDocument().toJson());
        System.out.println("over");

    }
}
