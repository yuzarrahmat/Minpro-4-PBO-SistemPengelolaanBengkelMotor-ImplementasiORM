package dao;

import config.DatabaseConfiglrgn;
import model.LayananServis;
import model.PerbaikanBerat;
import model.ServisRutin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LayananServisDAOImpl implements LayananServisDAO {

    @Override
    public boolean save(LayananServis servis) {
        String sql = "INSERT INTO layanan_servis (kode_servis, plat_nomor, nama_pelanggan, biaya_jasa, kategori, detail_layanan, biaya_tambahan) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfiglrgn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servis.getKodeServis());
            stmt.setString(2, servis.getPlatNomor());
            stmt.setString(3, servis.getNamaPelanggan());
            stmt.setDouble(4, servis.getBiayaJasa());
            stmt.setString(5, servis.getKategoriServis());

            if (servis instanceof ServisRutin sr) {
                stmt.setString(6, sr.getMerkOli());
                stmt.setDouble(7, sr.getHargaOli());
            } else if (servis instanceof PerbaikanBerat pb) {
                stmt.setString(6, pb.getNamaSparepart());
                stmt.setDouble(7, pb.getHargaSparepart());
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[ERROR] Gagal menyimpan data ke database: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<LayananServis> findAll() {
        List<LayananServis> list = new ArrayList<>();
        String sql = "SELECT * FROM layanan_servis";
        try (Connection conn = DatabaseConfiglrgn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String kode = rs.getString("kode_servis");
                String plat = rs.getString("plat_nomor");
                String nama = rs.getString("nama_pelanggan");
                double jasa = rs.getDouble("biaya_jasa");
                String kategori = rs.getString("kategori");
                String detail = rs.getString("detail_layanan");
                double tambahan = rs.getDouble("biaya_tambahan");

                if ("Servis Rutin".equalsIgnoreCase(kategori) || kategori.contains("Rutin")) {
                    list.add(new ServisRutin(kode, plat, nama, jasa, detail, tambahan));
                } else if ("Perbaikan Berat".equalsIgnoreCase(kategori)) {
                    list.add(new PerbaikanBerat(kode, plat, nama, jasa, detail, tambahan));
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Gagal mengambil data dari database: " + e.getMessage());
        }
        return list;
    }

    @Override
    public LayananServis findByKode(String kodeServis) {
        String sql = "SELECT * FROM layanan_servis WHERE kode_servis = ?";
        try (Connection conn = DatabaseConfiglrgn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, kodeServis);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String kode = rs.getString("kode_servis");
                    String plat = rs.getString("plat_nomor");
                    String nama = rs.getString("nama_pelanggan");
                    double jasa = rs.getDouble("biaya_jasa");
                    String kategori = rs.getString("kategori");
                    String detail = rs.getString("detail_layanan");
                    double tambahan = rs.getDouble("biaya_tambahan");

                    if ("Servis Rutin".equalsIgnoreCase(kategori) || kategori.contains("Rutin")) {
                        return new ServisRutin(kode, plat, nama, jasa, detail, tambahan);
                    } else if ("Perbaikan Berat".equalsIgnoreCase(kategori)) {
                        return new PerbaikanBerat(kode, plat, nama, jasa, detail, tambahan);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Gagal mencari data: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateBiayaJasa(String kodeServis, double biayaJasaBaru) {
        String sql = "UPDATE layanan_servis SET biaya_jasa = ? WHERE kode_servis = ?";
        try (Connection conn = DatabaseConfiglrgn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, biayaJasaBaru);
            stmt.setString(2, kodeServis);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[ERROR] Gagal memperbarui biaya jasa: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByKode(String kodeServis) {
        String sql = "DELETE FROM layanan_servis WHERE kode_servis = ?";
        try (Connection conn = DatabaseConfiglrgn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, kodeServis);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[ERROR] Gagal menghapus data dari database: " + e.getMessage());
            return false;
        }
    }
}