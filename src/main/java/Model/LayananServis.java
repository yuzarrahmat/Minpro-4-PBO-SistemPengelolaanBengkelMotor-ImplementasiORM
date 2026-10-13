package model;

import jakarta.persistence.*;
import service.LayananBengkel;

@Entity
@Table(name = "layanan_servis")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "kategori", discriminatorType = DiscriminatorType.STRING)
public abstract class LayananServis implements LayananBengkel {
    @Id
    @Column(name = "kode_servis")
    private String kodeServis;

    @Column(name = "plat_nomor", nullable = false)
    private String platNomor;

    @Column(name = "nama_pelanggan", nullable = false)
    private String namaPelanggan;

    @Column(name = "biaya_jasa", nullable = false)
    private double biayaJasa;

    public LayananServis() {
        this.kodeServis = "";
        this.platNomor = "";
        this.namaPelanggan = "";
        this.biayaJasa = 0;
    }

    public LayananServis(String kodeServis, String platNomor, String namaPelanggan, double biayaJasa) {
        this.kodeServis = kodeServis;
        this.platNomor = platNomor;
        this.namaPelanggan = namaPelanggan;
        setBiayaJasa(biayaJasa);
    }

    public String getKodeServis() { return kodeServis; }
    public String getPlatNomor() { return platNomor; }
    public String getNamaPelanggan() { return namaPelanggan; }
    public double getBiayaJasa() { return biayaJasa; }

    public void setBiayaJasa(double biayaJasa) {
        if (biayaJasa < 0) {
            throw new IllegalArgumentException("Biaya jasa tidak boleh bernilai negatif.");
        }
        this.biayaJasa = biayaJasa;
    }

    public abstract String getDetailLayanan();
}