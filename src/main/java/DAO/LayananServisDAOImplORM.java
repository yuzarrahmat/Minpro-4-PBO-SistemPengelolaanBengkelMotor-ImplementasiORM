package dao;

import jakarta.persistence.*;
import model.LayananServis;
import java.util.List;

public class LayananServisDAOImplORM implements LayananServisDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BengkelPU");

    @Override
    public boolean save(LayananServis servis) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(servis);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("[ORM ERROR] Gagal simpan data: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public List<LayananServis> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT l FROM LayananServis l", LayananServis.class).getResultList();
        }
    }

    @Override
    public LayananServis findByKode(String kodeServis) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(LayananServis.class, kodeServis);
        }
    }

    @Override
    public boolean updateBiayaJasa(String kodeServis, double biayaJasaBaru) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            LayananServis s = em.find(LayananServis.class, kodeServis);
            if (s != null) {
                s.setBiayaJasa(biayaJasaBaru);
                em.merge(s);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("[ORM ERROR] Gagal update biaya: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean deleteByKode(String kodeServis) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            LayananServis s = em.find(LayananServis.class, kodeServis);
            if (s != null) {
                em.remove(s);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            System.err.println("[ORM ERROR] Gagal hapus data: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }
}