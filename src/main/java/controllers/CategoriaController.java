package controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.Categoria;

public class CategoriaController {
    public String createCategoria(String nombre) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Categoria.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            session.beginTransaction();
            session.persist(categoria);
            session.getTransaction().commit();
            session.close();

            return "Categoría creada";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error al crear categoría";
    }

    public String deleteCategoria(int id) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Categoria.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            // Obtener la categoría actual a eliminar
            Categoria categoria = session.get(Categoria.class, id);

            // Validar que la categoría a eliminar exista
            if (categoria == null) return "Categoría no encontrada para ser eliminada.";

            session.remove(categoria);
            session.getTransaction().commit();
            session.close();

            return "Categoría eliminada";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error al eliminar categoría";
    }

    public String updateCategoria(int id, String nombre) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Categoria.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            // Obtener la categoría actual a actualizar
            Categoria categoria = session.get(Categoria.class, id);

            // Validar que la categoría a actualizar exista
            if (categoria == null) return "Categoría no encontrada para ser actualizada.";

            categoria.setNombre(nombre);
            session.merge(categoria);
            session.getTransaction().commit();
            session.close();

            return "Categoría actualizada";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error al actualizar categoría";
    }

    public String getCategoria(int id) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Categoria.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();

            // Obtener la categoría actual por su ID
            Categoria categoria = session.get(Categoria.class, id);

            // Validar que la categoría exista
            if (categoria == null) return "Categoría no encontrada.";

            session.getTransaction().commit();
            session.close();

            return "Información de la categoría: " + categoria.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error al obtener la categoría.";
    }
}
