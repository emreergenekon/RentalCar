package Business;

import Core.Helper;
import Dao.BrandDao;
import Entity.Brand;
import Entity.Model;

import java.util.ArrayList;

public class BrandManager {

    private final BrandDao brandDao;
    private final ModelManager modelManager;

    public BrandManager() {
        this.modelManager = new ModelManager();
        this.brandDao = new BrandDao();
    }

    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> brandRowList = new ArrayList<>();
        for (Brand brand : this.findAll()){
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = brand.getId();
            rowObject[i++] = brand.getName();
            brandRowList.add(rowObject);
        }
        return brandRowList;
    }

    public ArrayList<Brand> findAll(){
        return this.brandDao.findAll();
    }

    public boolean save(Brand brand){
        if (brand.getId() != 0){
            Helper.showMessage("error");
        }
        return this.brandDao.save(brand);
    }
    public Brand getById(int id){
        return this.brandDao.getById(id);
    }

    public boolean update(Brand brand){
        if (this.getById(brand.getId()) == null){
            Helper.showMessage("notFound");
        }
        return this.brandDao.update(brand);
    }
    public boolean delete(int id){
        if (this.getById(id) == null){
            Helper.showMessage(id + " ID kayıtlı marka bulunamadı");
            return false;
        }
        for (Model model: modelManager.getByListBrandId(id)){
            this.modelManager.delete(model.getId());
        }
        return this.brandDao.delete(id);
    }
}