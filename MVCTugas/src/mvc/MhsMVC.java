
package mvc;

public class MhsMVC {
    MhsView mhsview = new MhsView();
    MhsModel mhsmodel = new MhsModel();
    MhsDAO mhsdao = new MhsDAO();
    controller mhscontroller = new controller(mhsmodel, mhsview, mhsdao);
}
