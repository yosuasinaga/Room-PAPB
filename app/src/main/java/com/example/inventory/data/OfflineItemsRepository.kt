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

import kotlinx.coroutines.flow.Flow

/**
 * Implementasi offline dari [ItemsRepository] yang menggunakan [ItemDao]
 * untuk berinteraksi dengan basis data lokal.
 *
 * Repository ini mengelola operasi CRUD (Create, Read, Update, Delete) item
 * melalui DAO yang terhubung ke basis data Room.
 *
 * @property itemDao Data Access Object (DAO) untuk melakukan operasi database terkait `Item`.
 */
class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {

    /**
     * Mengambil seluruh item dari basis data dalam bentuk aliran (stream) `Flow`.
     *
     * Menggunakan fungsi `getAllItems()` dari `itemDao` untuk mengembalikan daftar item
     * secara real-time yang dapat diobservasi.
     *
     * @return Flow dari daftar `Item` yang tersedia di basis data.
     */
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()

    /**
     * Mengambil satu item berdasarkan ID dari basis data.
     *
     * Menggunakan fungsi `getItem(id)` dari `itemDao` untuk mengembalikan `Flow` dari
     * item tertentu. Penggunaan `Flow` memungkinkan UI untuk mengamati perubahan pada data.
     *
     * @param id ID dari item yang ingin diambil.
     * @return Flow dari item yang sesuai dengan ID, atau `null` jika tidak ditemukan.
     */
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)

    /**
     * Menyisipkan (insert) item baru ke dalam basis data.
     *
     * Fungsi ini menggunakan `suspend` agar dapat dijalankan secara asynchronous,
     * sehingga tidak mengganggu main thread.
     *
     * @param item Item yang akan disisipkan ke dalam basis data.
     */
    override suspend fun insertItem(item: Item) = itemDao.insert(item)

    /**
     * Menghapus item dari basis data.
     *
     * Fungsi ini juga `suspend` agar proses penghapusan dapat dilakukan secara asynchronous,
     * menjaga agar tidak terjadi blocking pada thread utama.
     *
     * @param item Item yang akan dihapus dari basis data.
     */
    override suspend fun deleteItem(item: Item) = itemDao.delete(item)

    /**
     * Memperbarui data item yang ada di basis data.
     *
     * Fungsi `update` menggunakan `suspend` agar bisa dijalankan di luar main thread,
     * sehingga operasi pembaruan tidak mengganggu performa UI.
     *
     * @param item Item yang akan diperbarui di basis data.
     */
    override suspend fun updateItem(item: Item) = itemDao.update(item)
}
