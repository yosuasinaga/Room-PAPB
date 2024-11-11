/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Anda tidak diperbolehkan menggunakan file ini kecuali sesuai dengan ketentuan lisensi.
 * Anda dapat memperoleh salinan lisensi di:
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Kecuali diwajibkan oleh hukum atau disetujui secara tertulis, file ini
 * disediakan dalam kondisi "APA ADANYA" tanpa jaminan apa pun, baik tersurat maupun tersirat.
 * Lihat lisensi untuk informasi lebih lanjut.
 */

package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Mendefinisikan basis data Room untuk aplikasi inventaris.
 *
 * @Database adalah anotasi Room untuk menandai kelas ini sebagai basis data.
 * Parameter:
 * - entities: daftar kelas entitas (dalam hal ini [Item]) yang terkait dengan basis data.
 * - version: versi basis data, yang harus diupdate setiap ada perubahan skema.
 * - exportSchema: diatur ke false untuk menonaktifkan ekspor skema.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    /**
     * Mendefinisikan akses ke DAO (Data Access Object) untuk entitas Item.
     * Fungsi ini akan menyediakan akses ke operasi CRUD di tabel `item` dalam basis data.
     */
    abstract fun itemDao(): ItemDao

    companion object {
        // Menyimpan instance tunggal dari InventoryDatabase untuk mencegah pemborosan resource.
        @Volatile
        private var Instance: InventoryDatabase? = null

        /**
         * Mendapatkan instance dari InventoryDatabase.
         *
         * Fungsi ini mengembalikan instance database yang sudah ada jika sudah diinisialisasi.
         * Jika belum, maka fungsi ini akan membuat instance baru dengan menggunakan
         * `Room.databaseBuilder`, lalu menyimpannya dalam `Instance` agar hanya ada satu instance
         * di seluruh aplikasi. Penggunaan `synchronized` memastikan inisialisasi aman pada lingkungan multi-thread.
         */
        fun getDatabase(context: Context): InventoryDatabase {
            // Jika Instance tidak null, kembalikan, jika null buat instance database baru.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build().also { Instance = it }
            }
        }
    }
}
