
package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class controller {
    MhsModel mhsmodel;
    MhsView mhsview;
    MhsDAO mhsdao;
    
    public controller(MhsModel mhsmodel, MhsView mhsview, MhsDAO mhsdao){
        this.mhsmodel = mhsmodel;
        this.mhsview = mhsview;
        this.mhsdao = mhsdao;
        
        if(mhsdao.getJmldata() != 0){
            String dataMahasiswa[][] = mhsdao.readMahasiswa();
            mhsview.tabel.setModel((new JTable(dataMahasiswa, mhsview.namaKolom)).getModel());
        }else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada");
        }
        mhsview.simpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String nim = mhsview.getNim();
               String nama = mhsview.getNama();
               String alamat = mhsview.getAlamat();
               if(nim.isEmpty() || nama.isEmpty() || alamat.isEmpty()){
                   JOptionPane.showMessageDialog(null, "Harap Isi Semua Field");
               }else{
                   mhsmodel.setMhsModel(nim, nama, alamat);
                   mhsdao.Insert(mhsmodel);
                   
                   String dataMahasiswa[][] = mhsdao.readMahasiswa();
                   mhsview.tabel.setModel((new JTable(dataMahasiswa, mhsview.namaKolom)).getModel());
               }
            }
        });
        mhsview.delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String nim = mhsview.getNim();
               String nama = mhsview.getNama();
               String alamat = mhsview.getAlamat();
               if(nim.isEmpty() || nama.isEmpty() || alamat.isEmpty()){
                   JOptionPane.showMessageDialog(null, "Harap Isi Semua Field");
               }else{
                   mhsmodel.setMhsModel(nim, nama, alamat);
                   mhsdao.Delete(mhsmodel);
                   
                   String dataMahasiswa[][] = mhsdao.readMahasiswa();
                   mhsview.tabel.setModel((new JTable(dataMahasiswa, mhsview.namaKolom)).getModel());
               }
            }
        });
    }
}
