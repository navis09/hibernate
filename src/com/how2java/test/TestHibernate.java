package com.how2java.test;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class TestHibernate {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Add Product
//        Product product = new Product();
//        product.setName("product1");
//        product.setPrice(55);
//        session.save(product);

        // Add Category
//        Category category = new Category();
//        category.setName("分类1");

        // Batch Add Product
//        for (int i = 2; i < 10; i++) {
//            Product p = new Product();
//            p.setName("product" + i);
//            p.setPrice(i * 10);
//            session.save(p);
//        }

        // Get Product based on its 'id'
        Product productS = session.get(Product.class, 4);
        System.out.println("id = 4的Product的名称为： " + productS.getName());

        // Delete
        Product productD = session.get(Product.class, 10);
        if (productD != null) {
            session.delete(productD);
            System.out.println("Product已删除");
        } else {
            System.out.println("该id的对象不存在");
        }

        // Update
        Product productU = session.get(Product.class, 8);
        productU.setName("product8Updated");
        session.update(productU);

        // Query
        String hql0 = "from Product where name like :myname";
        String hql1 = "from Product where name like ?0";
        Query<Product> query = session.createQuery(hql0);
        query.setParameter("myname", "%product%");
        List<Product> list = query.list();
        for (Product product : list) {
            System.out.println(product.getName());
        }

        // Criteria @deprecated

        // SQL Query
        String sql = "select * from product_ p where p.name like '%product%'";
        Query query1 = session.createSQLQuery(sql);
        List<Object[]> queryList= query1.list();
        for (Object[] os : queryList) {
            for (Object filed: os) {
                System.out.print(filed+"\t");
            }
            System.out.println();
        }



        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
