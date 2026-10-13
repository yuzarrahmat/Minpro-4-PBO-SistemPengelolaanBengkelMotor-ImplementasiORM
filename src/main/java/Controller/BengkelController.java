package controller;

import dao.LayananServisDAO;
import dao.LayananServisDAOImplORM;
import model.LayananServis;

import java.util.List;

public class BengkelController {
    private final LayananServisDAO dao;

    public BengkelController() {
    this.dao = new LayananServisDAOImplORM();
}

    public String generateKodeServis() {
        List<LayananServis> list = dao.findAll();
        int nextId = list.size() + 1;
        return String.format("SRV-%03d", nextId);
    }

    public boolean tambahServis(LayananServis s) {
        return dao.save(s);
    }

    public boolean tambahServis(LayananServis s, String catatanTeknisi) {
        boolean success = dao.save(s);
        if (success) {
            System.out.println("[LOG TEKNISI]: " + catatanTeknisi);
        }
        return success;
    }

    public List<LayananServis> getAllAntrean() {
        return dao.findAll();
    }

    public LayananServis cariByKode(String kodeServis) {
        return dao.findByKode(kodeServis);
    }

    public boolean updateBiayaJasa(String kodeServis, double biayaJasaBaru) {
        return dao.updateBiayaJasa(kodeServis, biayaJasaBaru);
    }

    public boolean selesaikanServis(String kodeServis) {
        return dao.deleteByKode(kodeServis);
    }
}