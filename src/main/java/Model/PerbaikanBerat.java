package model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Perbaikan Berat")
public class PerbaikanBerat extends LayananServis {
    @Column(name = "detail_layanan")
    private String namaSparepart;

    @Column(name = "biaya_tambahan")
    private double hargaSparepart;

    public PerbaikanBerat() { super(); }

    public PerbaikanBerat(String kodeServis, String platNomor, String namaPelanggan, double biayaJasa, String namaSparepart, double hargaSparepart) {
        super(kodeServis, platNomor, namaPelanggan, biayaJasa);
        if (hargaSparepart < 0) throw new IllegalArgumentException("Harga sparepart tidak boleh negatif.");
        this.namaSparepart = namaSparepart;
        this.hargaSparepart = hargaSparepart;
    }

    public String getNamaSparepart() { return namaSparepart; }
    public double getHargaSparepart() { return hargaSparepart; }

    @Override public double hitungTotalBiaya() { return getBiayaJasa() + hargaSparepart; }
    @Override public String getKategoriServis() { return "Perbaikan Berat"; }
    @Override public String getDetailLayanan() { return "Sparepart: " + namaSparepart + " (Rp " + hargaSparepart + ")"; }
}