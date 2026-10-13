package dao;

import model.LayananServis;
import java.util.List;

public interface LayananServisDAO {
    boolean save(LayananServis servis);
    List<LayananServis> findAll();
    LayananServis findByKode(String kodeServis);
    boolean updateBiayaJasa(String kodeServis, double biayaJasaBaru);
    boolean deleteByKode(String kodeServis);
}