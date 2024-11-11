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
 * Repository yang menyediakan metode untuk memasukkan (insert), memperbarui (update),
 * menghapus (delete), dan mengambil (retrieve) [Item] dari sumber data tertentu.
 *
 * Interface ini bertindak sebagai abstraksi dari sumber data, sehingga implementasi spesifik
 * dari sumber data (misalnya database lokal, jaringan, dsb.) dapat digunakan dengan cara yang sama.
 */
interface ItemsRepository {
    /**
     * Mengambil seluruh item dari sumber data dalam bentuk aliran (stream) data.
     *
     * Mengembalikan `Flow<List<Item>>`, memungkinkan observasi perubahan data di UI
     * secara real-time ketika ada pembaruan pada item.
     */
    fun getAllItemsStream(): Flow<List<Item>>

    /**
     * Mengambil satu item dari sumber data yang sesuai dengan [id] tertentu.
     *
     * Mengembalikan `Flow<Item?>` sehingga perubahan pada data ini dapat diamati.
     * Jika item tidak ditemukan, akan mengembalikan `null`.
     *
     * @param id ID dari item yang ingin diambil.
     */
    fun getItemStream(id: Int): Flow<Item?>

    /**
     * Menyisipkan (insert) item ke dalam sumber data.
     *
     * Menggunakan `suspend` agar fungsi ini dapat dipanggil secara asynchronous,
     * memungkinkan operasi ini berjalan di luar main thread.
     *
     * @param item Item yang akan disisipkan ke dalam sumber data.
     */
    suspend fun insertItem(item: Item)

    /**
     * Menghapus item dari sumber data.
     *
     * Fungsi ini menggunakan `suspend` agar bisa dijalankan di luar main thread,
     * mencegah blocking pada thread utama.
     *
     * @param item Item yang akan dihapus dari sumber data.
     */
    suspend fun deleteItem(item: Item)

    /**
     * Memperbarui (update) item yang sudah ada dalam sumber data.
     *
     * Menggunakan `suspend` agar operasi ini dapat berjalan secara asynchronous.
     *
     * @param item Item yang akan diperbarui dalam sumber data.
     */
    suspend fun updateItem(item: Item)
}
