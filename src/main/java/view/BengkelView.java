package view;

import controller.BengkelController;
import model.LayananServis;
import model.PerbaikanBerat;
import model.ServisRutin;
import util.InputValidator;

import java.util.List;

public class BengkelView {
    private final BengkelController controller;

    public BengkelView(BengkelController controller) {
        this.controller = controller;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n======================================");
            System.out.println("   SISTEM PENGELOLAAN BENGKEL MOTORRR   ");
            System.out.println("========================================");
            System.out.println("1. Daftar Antrean Servis");
            System.out.println("2. Catat Servis Rutin / Ganti Oli");
            System.out.println("3. Catat Perbaikan Berat");
            System.out.println("4. Update Biaya Jasa Servis");
            System.out.println("5. Selesaikan Servis & Cetak Struk");
            System.out.println("6. Keluar");

            int pilihan = InputValidator.readIntRange("Pilih Menu [1-6]: ", 1, 6);

            switch (pilihan) {
                case 1 -> tampilkanAntrean();
                case 2 -> tambahServisRutin();
                case 3 -> tambahPerbaikanBerat();
                case 4 -> updateBiayaJasa();
                case 5 -> prosesSelesai();
                case 6 -> {
                    System.out.println("\nTerima kasih!");
                    running = false;
                }
            }
        }
    }

    private void tampilkanAntrean() {
        System.out.println("\n--- DAFTAR ANTREAN SERVIS ---");
        List<LayananServis> list = controller.getAllAntrean();
        if (list.isEmpty()) {
            System.out.println("Tidak ada antrean servis saat ini.");
            return;
        }

        for (LayananServis s : list) {
            System.out.println("[" + s.getKodeServis() + "] Plat: " + s.getPlatNomor() + " | Pemilik: " + s.getNamaPelanggan());
            System.out.println("    Kategori : " + s.getKategoriServis());
            System.out.println("    Detail   : " + s.getDetailLayanan());
            System.out.println("    Biaya Jasa: Rp " + s.getBiayaJasa());
            System.out.println("    Total    : Rp " + s.hitungTotalBiaya());
            System.out.println("--------------------------------------------------");
        }
    }

    private void tambahServisRutin() {
        System.out.println("\n--- TAMBAH SERVIS RUTIN & GANTI OLI ---");
        String kode = controller.generateKodeServis();
        String plat = InputValidator.readNonEmptyString("Masukkan Plat Nomor Motor: ");
        String nama = InputValidator.readNonEmptyString("Masukkan Nama Pelanggan: ");
        double jasa = InputValidator.readNonNegativeDouble("Masukkan Biaya Jasa (Rp): ");
        String oli = InputValidator.readNonEmptyString("Masukkan Merk Oli: ");
        double hargaOli = InputValidator.readNonNegativeDouble("Masukkan Harga Oli (Rp): ");

        ServisRutin servis = new ServisRutin(kode, plat, nama, jasa, oli, hargaOli);
        controller.tambahServis(servis, "Pengecekan rutin dan pergantian oli " + oli + " dicatat.");
        System.out.println("Servis Rutin berhasil dicatat! Kode: " + kode);
    }

    private void tambahPerbaikanBerat() {
        System.out.println("\n--- TAMBAH PERBAIKAN BERAT ---");
        String kode = controller.generateKodeServis();
        String plat = InputValidator.readNonEmptyString("Masukkan Plat Nomor Motor: ");
        String nama = InputValidator.readNonEmptyString("Masukkan Nama Pelanggan: ");
        double jasa = InputValidator.readNonNegativeDouble("Masukkan Biaya Jasa (Rp): ");
        String sparepart = InputValidator.readNonEmptyString("Masukkan Nama Sparepart pengganti: ");
        double hargaSparepart = InputValidator.readNonNegativeDouble("Masukkan Harga Sparepart (Rp): ");

        PerbaikanBerat perbaikan = new PerbaikanBerat(kode, plat, nama, jasa, sparepart, hargaSparepart);
        controller.tambahServis(perbaikan);
        System.out.println("Perbaikan Berat berhasil dicatat! Kode: " + kode);
    }

    private void updateBiayaJasa() {
        System.out.println("\n--- UPDATE BIAYA JASA ---");
        String kode = InputValidator.readNonEmptyString("Masukkan Kode Servis : ");
        LayananServis s = controller.cariByKode(kode);
        if (s == null) {
            System.out.println("Kode servis gaada!");
            return;
        }

        double jasaBaru = InputValidator.readNonNegativeDouble("Masukkan Biaya Jasa Baru (Rp): ");
        controller.updateBiayaJasa(kode, jasaBaru);
        System.out.println("Biaya jasa untuk " + kode + " berhasil diperbarui menjadi Rp " + jasaBaru);
    }

    private void prosesSelesai() {
        System.out.println("\n--- PROSES SELESAI SERVIS & STRUK ---");
        String kode = InputValidator.readNonEmptyString("Masukkan Kode Servis: ");
        LayananServis s = controller.cariByKode(kode);

        if (s != null) {
            System.out.println("\n================ STRUK BENGKEL MOTOR ================");
            System.out.println("Kode Servis : " + s.getKodeServis());
            System.out.println("Plat Nomor  : " + s.getPlatNomor());
            System.out.println("Pelanggan   : " + s.getNamaPelanggan());
            System.out.println("Kategori    : " + s.getKategoriServis());
            System.out.println("Detail      : " + s.getDetailLayanan());
            System.out.println("Biaya Jasa  : Rp " + s.getBiayaJasa());
            System.out.println("-----------------------------------------------------");
            System.out.println("TOTAL BAYAR : Rp " + s.hitungTotalBiaya());
            System.out.println("=====================================================");

            controller.selesaikanServis(kode);
            System.out.println("Servis telah selesai dan data berhasil dihapus dari antrean.");
        } else {
            System.out.println("Kode servis gaada!");
        }
    }
}