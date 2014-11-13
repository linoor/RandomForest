package RandomForestHOG.RandomForest;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import levis.classifier.Classifier;
import levis.learner.Learner;
import levis.sample.DataBase;

@objid ("f13ea57b-2648-48bf-8e5e-b1319a05eaba")
public @interface RandomForestLearner  extends Learner {
    @objid ("eafec0c5-2d8e-48a2-b23c-cb59b300755a")
    int _attrN();

    @objid ("462886f4-e68d-48c1-b2dd-1b27d8dc39a2")
    int _trainN();

    @objid ("89e7d33d-e15d-4fd6-b44f-324059a4e1f8")
    RandomForest _model();

    @objid ("1eaa0854-8e11-4e6b-8a90-5a8d3e57821e")
    public RandomForestLearner() {
    }

    @objid ("4aff82c9-54dd-4e50-960f-3fcb7c21264b")
    protected Object getLearnerParameter(String p0) {
        // TODO Auto-generated return
        return null;
    }

    @objid ("5f02503d-159a-4a86-beea-64e6de3555ee")
    protected String getLearnerParameterDescription(String p0) {
        // TODO Auto-generated return
        return null;
    }

    @objid ("09f39372-e4c2-40ef-be1d-63f4a85ee741")
    protected String[] getLearnerParameters() {
    }

    @objid ("ed4da577-e9c1-44db-b292-1ddb4aeab813")
    protected Object[] getLearnerPossibleValues(String p0) {
    }

    @objid ("84799113-d630-4eef-bd4d-a074581d5b7f")
    protected Classifier learn(DataBase p0) throws Exception {
        // TODO Auto-generated return
        return null;
    }

    @objid ("9b2f1c61-6be7-4156-85db-15202c387c02")
    protected void setLearnerParameter(String p0, Object p1) throws Exception {
    }

    @objid ("0b5c008f-76ac-4c0e-b2de-4a29ece8a2f6")
    public Float testAccuracy(DataBase testData) {
        // TODO Auto-generated return
        return 0f;
    }

}
