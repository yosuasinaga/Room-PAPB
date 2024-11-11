/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Anda tidak diperbolehkan menggunakan file ini kecuali sesuai dengan ketentuan lisensi.
 * Anda dapat memperoleh salinan lisensinya di:
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Kecuali diwajibkan oleh hukum atau disetujui secara tertulis, file ini
 * disediakan dalam kondisi "APA ADANYA" tanpa jaminan apa pun, baik tersurat maupun tersirat.
 * Lihat lisensi untuk informasi lebih lanjut.
 */

package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) untuk mengelola operasi database pada entitas `Item`.
 *
 * Anotasi @Dao menandai interface ini sebagai DAO untuk Room, yang menyediakan metode
 * akses ke basis data untuk entitas `Item`. Semua fungsi di dalamnya merupakan operasi
 * database, seperti menambah, mengubah, menghapus, dan mengambil data.
 */
@Dao
interface ItemDao {

    /**
     * Menyisipkan (insert) item baru ke dalam database.
     *
     * Jika terjadi konflik (misalnya ID sudah ada), maka item tersebut diabaikan,
     * sesuai dengan strategi `OnConflictStrategy.IGNORE`.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /**
     * Memperbarui (update) data item yang sudah ada di dalam database.
     *
     * Fungsi ini menggunakan `suspend` agar bisa dijalankan secara asynchronous.
     */
    @Update
    suspend fun update(item: Item)

    /**
     * Menghapus (delete) item tertentu dari database.
     *
     * Fungsi ini juga menggunakan `suspend` agar operasi dapat dilakukan secara asynchronous.
     */
    @Delete
    suspend fun delete(item: Item)

    /**
     * Mengambil satu item dari database berdasarkan ID.
     *
     * Fungsi ini mengembalikan data dalam bentuk `Flow` agar dapat diamati (observed),
     * sehingga setiap perubahan pada item ini di database akan langsung ter-update di UI.
     *
     * @param id ID dari item yang ingin diambil.
     * @return Flow dari objek `Item` yang sesuai dengan ID yang diberikan.
     */
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    /**
     * Mengambil seluruh item dari database dan mengurutkannya berdasarkan nama secara ascending.
     *
     * Fungsi ini juga mengembalikan `Flow` dari daftar item (`List<Item>`) agar perubahan
     * dalam data dapat langsung teramati pada UI.
     *
     * @return Flow dari daftar `Item` yang diurutkan berdasarkan nama dalam urutan naik (ascending).
     */
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}
