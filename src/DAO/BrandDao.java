package Dao;

import Core.Db;
import Entity.Brand;
import Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrandDao {

    private final Connection con;

    public BrandDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Brand> findAll() {
        ArrayList<Brand> brandList  = new ArrayList<>();
        String sqlQuery = "SELECT * FROM public.brand ORDER BY brand_id";
        try {
            ResultSet resultSet = con.createStatement().executeQuery(sqlQuery);
            while (resultSet.next()) {
                brandList.add(this.match(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brandList;
    }

    public Brand getById(int id){
        Brand obj = null;
        String query = "SELECT * FROM public.brand WHERE brand_id = ?";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    public boolean save(Brand brand){
        String query = "INSERT INTO public.brand (brand_name) VALUES (?)";
        try{
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1,brand.getName());
            return ps.executeUpdate() != -1;
        }catch (SQLException e) {

        }
        return true;
    }

    public boolean update(Brand brand){
        String query = "UPDATE public.brand SET brand_name = ? WHERE brand_id = ? ";
        try{
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1,brand.getName());
            ps.setInt(2,brand.getId());
            return ps.executeUpdate() != -1;
        }catch (SQLException e) {

        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.brand WHERE brand_id = ?";
        try {
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1,id);
            return ps.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public Brand match(ResultSet rs) throws SQLException {
        Brand obj = new Brand();
        obj.setId(rs.getInt("brand_id"));
        obj.setName(rs.getString("brand_name"));

        return obj;
    }
}