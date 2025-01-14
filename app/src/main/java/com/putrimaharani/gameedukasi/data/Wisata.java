package com.putrimaharani.gameedukasi.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Wisata implements Parcelable {
    // Properti wisata
    private String nama;
    private String deskripsi;
    private int gambar;

    // Konstruktor
    public Wisata(String nama, String deskripsi, int gambar) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.gambar = gambar;
    }

    // Konstruktor Parcelable
    protected Wisata(Parcel in) {
        nama = in.readString();
        deskripsi = in.readString();
        gambar = in.readInt();
    }

    // CREATOR untuk Parcelable
    public static final Creator<Wisata> CREATOR = new Creator<Wisata>() {
        @Override
        public Wisata createFromParcel(Parcel in) {
            return new Wisata(in);
        }

        @Override
        public Wisata[] newArray(int size) {
            return new Wisata[size];
        }
    };

    // Getter untuk properti
    public String getNama() {
        return nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getGambar() {
        return gambar;
    }

    // Alias getter (opsional, untuk kompatibilitas dengan kode lain)
    public String getTitle() {
        return getNama();
    }

    public String getDescription() {
        return getDeskripsi();
    }

    public int getImageResId() {
        return getGambar();
    }

    // Metode Parcelable
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(deskripsi);
        dest.writeInt(gambar);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
